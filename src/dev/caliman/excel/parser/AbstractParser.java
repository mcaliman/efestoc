/*
 * Efesto - Excel Formula Extractor System and Topological Ordering algorithm.
 * Copyright (C) 2017 Massimo Caliman mcaliman@gmail.com
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 * If AGPL Version 3.0 terms are incompatible with your use of
 * Efesto, alternative license terms are available from Massimo Caliman
 * please direct inquiries about Efesto licensing to mcaliman@gmail.com
 */

package dev.caliman.excel.parser;

import dev.caliman.excel.grammar.lexicaltokens.CELL;
import dev.caliman.excel.grammar.lexicaltokens.RANGE;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.formula.EvaluationCell;
import org.apache.poi.ss.formula.EvaluationName;
import org.apache.poi.ss.formula.FormulaParseException;
import org.apache.poi.ss.formula.ptg.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFEvaluationWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.lang.System.err;
import static org.apache.poi.ss.formula.ptg.ErrPtg.*;
import static org.apache.poi.ss.usermodel.CellType.*;

public abstract class AbstractParser {

    final Predicate<Ptg> arrayPtg = (Ptg t) -> t instanceof ArrayPtg;
    final Predicate<Ptg> addPtg = (Ptg t) -> t instanceof AddPtg;
    final Predicate<Ptg> area3DPxg = (Ptg t) -> t instanceof Area3DPxg;
    final Predicate<Ptg> areaErrPtg = (Ptg t) -> t instanceof AreaErrPtg;
    final Predicate<Ptg> areaPtg = (Ptg t) -> t instanceof AreaPtg;
    final Predicate<Ptg> attrPtg = (Ptg t) -> t instanceof AttrPtg;
    final Predicate<Ptg> boolPtg = (Ptg t) -> t instanceof BoolPtg;
    final Predicate<Ptg> concatPtg = (Ptg t) -> t instanceof ConcatPtg;
    final Predicate<Ptg> deleted3DPxg = (Ptg t) -> t instanceof Deleted3DPxg;
    final Predicate<Ptg> deletedArea3DPtg = (Ptg t) -> t instanceof DeletedArea3DPtg;
    final Predicate<Ptg> deletedRef3DPtg = (Ptg t) -> t instanceof DeletedRef3DPtg;
    final Predicate<Ptg> dividePtg = (Ptg t) -> t instanceof DividePtg;
    final Predicate<Ptg> equalPtg = (Ptg t) -> t instanceof EqualPtg;
    final Predicate<Ptg> errPtg = (Ptg t) -> t instanceof ErrPtg;
    final Predicate<Ptg> funcPtg = (Ptg t) -> t instanceof FuncPtg;
    final Predicate<Ptg> funcVarPtg = (Ptg t) -> t instanceof FuncVarPtg;
    final Predicate<Ptg> greaterEqualPtg = (Ptg t) -> t instanceof GreaterEqualPtg;
    final Predicate<Ptg> greaterThanPtg = (Ptg t) -> t instanceof GreaterThanPtg;
    final Predicate<Ptg> intersectionPtg = (Ptg t) -> t instanceof IntersectionPtg;
    final Predicate<Ptg> intPtg = (Ptg t) -> t instanceof IntPtg;
    final Predicate<Ptg> lessEqualPtg = (Ptg t) -> t instanceof LessEqualPtg;
    final Predicate<Ptg> lessThanPtg = (Ptg t) -> t instanceof LessThanPtg;
    final Predicate<Ptg> memErrPtg = (Ptg t) -> t instanceof MemErrPtg;
    final Predicate<Ptg> missingArgPtg = (Ptg t) -> t instanceof MissingArgPtg;
    final Predicate<Ptg> multiplyPtg = (Ptg t) -> t instanceof MultiplyPtg;
    final Predicate<Ptg> namePtg = (Ptg t) -> t instanceof NamePtg;
    final Predicate<Ptg> notEqualPtg = (Ptg t) -> t instanceof NotEqualPtg;
    final Predicate<Ptg> numberPtg = (Ptg t) -> t instanceof NumberPtg;
    final Predicate<Ptg> parenthesisPtg = (Ptg t) -> t instanceof ParenthesisPtg;
    final Predicate<Ptg> percentPtg = (Ptg t) -> t instanceof PercentPtg;
    final Predicate<Ptg> powerPtg = (Ptg t) -> t instanceof PowerPtg;
    final Predicate<Ptg> ref3DPxg = (Ptg t) -> t instanceof Ref3DPxg;
    final Predicate<Ptg> refErrorPtg = (Ptg t) -> t instanceof RefErrorPtg;
    final Predicate<Ptg> refPtg = (Ptg t) -> t instanceof RefPtg;
    final Predicate<Ptg> stringPtg = (Ptg t) -> t instanceof StringPtg;
    final Predicate<Ptg> subtractPtg = (Ptg t) -> t instanceof SubtractPtg;
    final Predicate<Ptg> unaryMinusPtg = (Ptg t) -> t instanceof UnaryMinusPtg;
    final Predicate<Ptg> unaryPlusPtg = (Ptg t) -> t instanceof UnaryPlusPtg;
    final Predicate<Ptg> unionPtg = (Ptg t) -> t instanceof UnionPtg;
    final Predicate<Ptg> unknownPtg = (Ptg t) -> t instanceof UnknownPtg;
    private final SpreadsheetVersion SPREADSHEET_VERSION = SpreadsheetVersion.EXCEL2007;
    private final String filename;

    Workbook workbook;
    Sheet sheet;
    Ptg[] formulaPtgs;
    String formulaAddress;
    String formulaPlainText;
    int noOfFormulas;//formula counters noOfFormulas
    boolean singleSheet;//is single sheet or not?
    int column;//Current Formula Column
    int row;//Current Formula Row
    private XSSFEvaluationWorkbook evaluation;


    AbstractParser(String filename) throws IOException, InvalidFormatException {
        this.filename = filename;
        File file = new File(this.filename);
        this.workbook = WorkbookFactory.create(file);
    }

    public static String cellAddress(final int row, final int column, final String sheetName) {
        return sheetName != null ?
                sheetName + "!" + AbstractParser.cellAddress(row, column) :
                AbstractParser.cellAddress(row, column);

    }

    public static String cellAddress(final int row, final int column) {
        String letter = AbstractParser.columnAsLetter(column);
        return (letter + (row + 1));
    }

    public static String columnAsLetter(int column) {
        int columnNumber = column + 1;
        StringBuilder string = new StringBuilder(2);
        int colRemain = columnNumber;
        while(colRemain > 0) {
            int thisPart = colRemain % 26;
            if(thisPart == 0) thisPart = 26;
            colRemain = (colRemain - thisPart) / 26;
            char colChar = (char) (thisPart + 64);
            string.insert(0, colChar);
        }
        return string.toString();
    }

    public static String quote(String text) {
        return "\"" + text + "\"";
    }

    public static String quoteIf(String text) {
        return AbstractParser.hasSpaces(text) ? "'" + text.trim() + "'" : text.trim();
    }

    private static boolean hasSpaces(String text) {
        return text != null && text.trim().contains(" ");
    }

    public String getFilename() {
        return this.filename;
    }

    public void parse() {
        this.evaluation = XSSFEvaluationWorkbook.create((XSSFWorkbook) this.workbook);
        int noOfSheets = this.workbook.getNumberOfSheets();
        this.singleSheet = noOfSheets == 1;
        Stream<Sheet> stream = StreamSupport.stream(this.workbook.spliterator(), false);
        stream.forEach(
                (sheet) -> {
                    this.sheet = sheet;
                    parseRows();
                }
        );
    }

    private void parseRows() {
        Stream<Row> stream = StreamSupport.stream(this.sheet.spliterator(), false);
        stream.forEachOrdered(this::parse);
    }

    private void parse(Row row) {
        Stream<Cell> stream = StreamSupport.stream(row.spliterator(), false);
        stream.parallel().filter(this::nonEmpty).forEachOrdered(this::parse);
    }

    protected abstract void parse(Cell cell);

    void parseFormula(Cell cell) {
        this.noOfFormulas++;
        this.column = cell.getColumnIndex();
        this.row = cell.getRowIndex();
        this.formulaAddress = getCellAddress();
        this.formulaPlainText = cell.getCellFormula();
        System.out.println("Formula Plain Text: " + this.formulaAddress + " = " + formulaPlainText);
        this.formulaPtgs = tokens();

    }

    String parseErrorText(ErrPtg t) {
        String text;
        if(t == NULL_INTERSECTION) text = "#NULL!";
        else if(t == DIV_ZERO) text = "#DIV/0!";
        else if(t == VALUE_INVALID) text = "#VALUE!";
        else if(t == REF_INVALID) text = "#REF!";
        else if(t == NAME_INVALID) text = "#NAME?";
        else if(t == NUM_ERROR) text = "#NUM!";
        else if(t == N_A) text = "#N/A";
        else text = "FIXME!";
        return text;
    }

    private Ptg[] tokens() {
        int index = this.getSheetIndex();
        var name = this.getSheetName();
        var evaluationSheet = this.evaluation.getSheet(index);
        Ptg[] ptgs = null;
        try {
            EvaluationCell evaluationCell = evaluationSheet.getCell(this.row, this.column);
            ptgs = this.evaluation.getFormulaTokens(evaluationCell);
        } catch(FormulaParseException e) {
            err.println("" + e.getMessage() + name + this.row + this.column);
        }
        return ptgs;
    }

    Ptg[] getName(NamePtg t) {
        EvaluationName evaluationName = this.evaluation.getName(t);
        return evaluationName.getNameDefinition();
    }

    String getNameText(NamePtg t) {
        return this.evaluation.getNameText(t);
    }

    private String cellAddress(final String sheetName) {
        return sheetName != null ? sheetName + "!" + cellAddress() : cellAddress();
    }

    private String cellAddress() {
        String letter = columnAsLetter(this.column);
        return (letter + (this.row + 1));
    }

    String getCellAddress() {
        return cellAddress(this.getSheetName());
    }

    int getSheetIndex() {
        return this.workbook.getSheetIndex(this.sheet);
    }

    int getSheetIndex(String sheetName) {
        return this.evaluation.getSheetIndex(sheetName);
    }

    int getSheetIndex(Cell cell) {
        return getSheetIndex(cell.getSheet().getSheetName());
    }

    String getSheetName() {
        return this.sheet.getSheetName();
    }

    String getSheetName(Cell cell) {
        return cell.getSheet().getSheetName();
    }

    boolean isFormula(final Cell cell) {
        return cell!=null && cell.getCellType()==FORMULA;
        //return cell.getCellFormula()!=null && cell.getCellFormula().trim().length()>0;
    }

    boolean nonEmpty(final Cell cell) {
        return !empty(cell);
    }

    private boolean empty(final Cell cell) {
        return emptyCell(cell) && emptyTextCell(cell);
    }

    private boolean emptyCell(Cell cell) {
        return cell == null /*|| cell.getCellType() == CELL_TYPE_BLANK*/;
    }

    private boolean emptyTextCell(Cell cell) {
        return cell.getCellType() == STRING && cell.getStringCellValue().trim().isEmpty();
    }

    void doesFormulaReferToDeletedCell() {
        err.println(getCellAddress() + " does formula refer to deleted cell");
    }

    void parseErrPtg(Ptg t) {
        err.println(t.getClass().getName() + ": " + t.toString());
    }

    void parseMissingArguments() {
        err.println("Missing ExcelFunction Arguments for cell: " + getCellAddress());
    }

    Object parseCellValue(Cell cell) {
        if(cell == null) return null;
        if(isDataType(cell)) return cell.getDateCellValue();



        switch(cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return cell.getNumericCellValue();
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case FORMULA:
                if(cellToStringEqualsTo(cell, "TRUE")) return true;
                else if(cellToStringEqualsTo(cell, "FALSE")) return false;
                return cell.toString();
            default:
                return null;
        }
    }

    private boolean cellToStringEqualsTo(Cell cell, String text) {
        return cell.toString() != null && cell.toString().equalsIgnoreCase(text);

    }

    private boolean isDataType(Cell cell) {
        return cell.getCellType() == NUMERIC && HSSFDateUtil.isCellDateFormatted(cell);
    }

    private List<Cell> list(String reference) {
        AreaReference area = new AreaReference(reference, SPREADSHEET_VERSION);
        return list(area);
    }

    private List<Cell> list(AreaReference ar) {
        CellReference[] allReferencedCells = ar.getAllReferencedCells();
        return list(allReferencedCells);
    }

    private List<Cell> list(CellReference[] allReferencedCells) {
        Stream<CellReference> stream = Arrays.stream(allReferencedCells);
        return list(stream);
    }

    private List<Cell> list(Stream<CellReference> stream) {
        List<Cell> list = new ArrayList<>();
        stream.forEach(
                referencedCell -> {
                    Row row = getRow(referencedCell);
                    if(row != null) {
                        Cell cell = getCell(row, referencedCell);
                        list.add(cell);
                    }
                }
        );
        return list;
    }

    private Row getRow(CellReference cell) {
        Sheet sheet = getSheet(cell);
        return getRow(sheet, cell);
    }

    private Sheet getSheet(CellReference cell) {
        return this.workbook.getSheet(cell.getSheetName());
    }

    private Row getRow(Sheet sheet, CellReference cell) {
        return sheet.getRow(cell.getRow());
    }

    private Cell getCell(Row row, CellReference cell) {
        return row.getCell(cell.getCol());
    }

    /**
     * Area3DPxg
     * Title: XSSF Area 3D Reference (Sheet + Area)
     * Description: Defined an area in an external or different sheet.
     * REFERENCE:
     * This is XSSF only, as it stores the sheet / book references in String form.
     * The HSSF equivalent using indexes is Area3DPtg
     * <p>
     * parseSheetPlusArea
     */
    RANGE parseRange(String sheetnamne, Area3DPxg t) {
        var rangeFirstRow = t.getFirstRow();
        var rangeFirstColumn = t.getFirstColumn();
        var rangeLastRow = t.getLastRow();
        var rangeLastColumn = t.getLastColumn();

        var range = emptyRange(rangeFirstRow, rangeFirstColumn, rangeLastRow, rangeLastColumn);

        String reference = range.toString();
        List<Cell> cells = list(sheetnamne + "!" + reference);

        Stream<Cell> stream = cells.stream().parallel();
        stream.filter(Objects::nonNull).map(this::parseCellValue).forEachOrdered(range::add);
        return range;
    }

    /**
     * AreaPtg t Specifies a rectangular area of cells A1:A4 for instance.
     */
    RANGE parseRange(Sheet sheet, AreaPtg t) {
        RANGE range;
        var rangeFirstRow = t.getFirstRow();
        var rangeFirstColumn = t.getFirstColumn();
        var rangeLastRow = t.getLastRow();
        var rangeLastColumn = t.getLastColumn();

        range = emptyRange(rangeFirstRow, rangeFirstColumn, rangeLastRow, rangeLastColumn);

        String reference = range.toString();

        List<Cell> cells = range(sheet, reference);
        Stream<Cell> stream = cells.stream().parallel();
        stream.filter(Objects::nonNull).map(this::parseCellValue).forEachOrdered(range::add);
        return range;

    }

    private RANGE emptyRange(int rangeFirstRow, int rangeFirstColumn, int rangeLastRow, int rangeLastColumn) {
        RANGE range;
        CELL cellFirst = new CELL(rangeFirstRow, rangeFirstColumn);
        CELL cellLast = new CELL(rangeLastRow, rangeLastColumn);
        range = new RANGE(cellFirst, cellLast);
        return range;
    }

    private List<Cell> range(Sheet sheet, String refs) {
        AreaReference area = new AreaReference(sheet.getSheetName() + "!" + refs, SPREADSHEET_VERSION);
        return list(area);
    }

    class WhatIf {

        final Ptg ptg;
        final Predicate<Ptg> predicate;
        final Consumer<Ptg> consumer;

        WhatIf(Ptg ptg, Predicate<Ptg> predicate, Consumer<Ptg> consumer) {
            this.ptg = ptg;
            this.predicate = predicate;
            this.consumer = consumer;
        }
    }
}
