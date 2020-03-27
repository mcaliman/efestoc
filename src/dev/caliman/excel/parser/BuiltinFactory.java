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

import dev.caliman.excel.grammar.functions.builtin.*;
import dev.caliman.excel.grammar.functions.builtin.logical.*;
import dev.caliman.excel.grammar.functions.builtin.math.ABS;
import dev.caliman.excel.grammar.functions.builtin.trigo.*;
import dev.caliman.excel.grammar.functions.conditionalreferencefunction.CHOOSE;
import dev.caliman.excel.grammar.functions.conditionalreferencefunction.IF;
import dev.caliman.excel.grammar.functions.referencefunction.INDEX;
import dev.caliman.excel.grammar.functions.referencefunction.INDIRECT;
import dev.caliman.excel.grammar.functions.referencefunction.OFFSET;
import dev.caliman.excel.grammar.nonterm.Formula;
import dev.caliman.excel.grammar.nonterm.Start;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

final class BuiltinFactory {

    private final static Logger LOG = Logger.getLogger(BuiltinFactory.class.getName());
    private final static Map<String, Class> clazzMap = new HashMap<>();

    static {
        clazzMap.put("ABS", ABS.class);
        clazzMap.put("ACCRINT", ACCRINT.class);
        clazzMap.put("ACCRINTM", ACCRINTM.class);
        clazzMap.put("ACOS", ACOS.class);
        clazzMap.put("ACOSH", ACOSH.class);
        clazzMap.put("ADDRESS", ADDRESS.class);
        clazzMap.put("AMORDEGRC", AMORDEGRC.class);
        clazzMap.put("AMORLINC", AMORLINC.class);
        clazzMap.put("AND", AND.class);
        clazzMap.put("AREAS", AREAS.class);
        clazzMap.put("ASC", ASC.class);
        clazzMap.put("ASIN", ASIN.class);
        clazzMap.put("ASINH", ASINH.class);
        clazzMap.put("ATAN", ATAN.class);
        clazzMap.put("ATAN2", ATAN2.class);
        clazzMap.put("ATANH", ATANH.class);
        clazzMap.put("AVEDEV", AVEDEV.class);
        clazzMap.put("AVERAGE", AVERAGE.class);
        clazzMap.put("AVERAGEA", AVERAGEA.class);
        clazzMap.put("AVERAGEIF", AVERAGEIF.class);
        clazzMap.put("AVERAGEIFS", AVERAGEIFS.class);
        clazzMap.put("BAHTTEXT", BAHTTEXT.class);
        clazzMap.put("BESSELI", BESSELI.class);
        clazzMap.put("BESSELJ", BESSELJ.class);
        clazzMap.put("BESSELK", BESSELK.class);
        clazzMap.put("BESSELY", BESSELY.class);
        clazzMap.put("BETADIST", BETADIST.class);
        clazzMap.put("BETAINV", BETAINV.class);
        clazzMap.put("BIN2DEC", BIN2DEC.class);
        clazzMap.put("BIN2HEX", BIN2HEX.class);
        clazzMap.put("BIN2OCT", BIN2OCT.class);
        clazzMap.put("BINOMDIST", BINOMDIST.class);
        clazzMap.put("CALL", CALL.class);
        clazzMap.put("CEILING", CEILING.class);
        clazzMap.put("CELL", CELL.class);
        clazzMap.put("CHAR", CHAR.class);
        clazzMap.put("CHIDIST", CHIDIST.class);
        clazzMap.put("CHIINV", CHIINV.class);
        clazzMap.put("CHITEST", CHITEST.class);
        clazzMap.put("CHOOSE", CHOOSE.class);
        clazzMap.put("CLEAN", CLEAN.class);
        clazzMap.put("CODE", CODE.class);
        clazzMap.put("COLUMN", COLUMN.class);
        clazzMap.put("COLUMNS", COLUMNS.class);
        clazzMap.put("COMBIN", COMBIN.class);
        clazzMap.put("COMPLEX", COMPLEX.class);
        clazzMap.put("CONCATENATE", CONCATENATE.class);
        clazzMap.put("CONFIDENCE", CONFIDENCE.class);
        clazzMap.put("CONVERT", CONVERT.class);
        clazzMap.put("CORREL", CORREL.class);
        clazzMap.put("COS", COS.class);
        clazzMap.put("COSH", COSH.class);
        clazzMap.put("COUNT", COUNT.class);
        clazzMap.put("COUNTA", COUNTA.class);
        clazzMap.put("COUNTBLANK", COUNTBLANK.class);
        clazzMap.put("COUNTIF", COUNTIF.class);
        clazzMap.put("COUNTIFS", COUNTIFS.class);
        clazzMap.put("COUPDAYBS", COUPDAYBS.class);
        clazzMap.put("COUPDAYS", COUPDAYS.class);
        clazzMap.put("COUPDAYSNC", COUPDAYSNC.class);
        clazzMap.put("COUPNCD", COUPNCD.class);
        clazzMap.put("COUPNUM", COUPNUM.class);
        clazzMap.put("COUPPCD", COUPPCD.class);
        clazzMap.put("COVAR", COVAR.class);
        clazzMap.put("CRITBINOM", CRITBINOM.class);
        clazzMap.put("CUBEKPIMEMBER", CUBEKPIMEMBER.class);
        clazzMap.put("CUBEMEMBER", CUBEMEMBER.class);
        clazzMap.put("CUBEMEMBERPROPERTY", CUBEMEMBERPROPERTY.class);
        clazzMap.put("CUBERANKEDMEMBER", CUBERANKEDMEMBER.class);
        clazzMap.put("CUBESET", CUBESET.class);
        clazzMap.put("CUBESETCOUNT", CUBESETCOUNT.class);
        clazzMap.put("CUBEVALUE", CUBEVALUE.class);
        clazzMap.put("CUMIPMT", CUMIPMT.class);
        clazzMap.put("CUMPRINC", CUMPRINC.class);
        clazzMap.put("DATE", DATE.class);
        clazzMap.put("DATEVALUE", DATEVALUE.class);
        clazzMap.put("DAVERAGE", DAVERAGE.class);
        clazzMap.put("DAY", DAY.class);
        clazzMap.put("DAYS360", DAYS360.class);
        clazzMap.put("DB", DB.class);
        clazzMap.put("DCOUNT", DCOUNT.class);
        clazzMap.put("DCOUNTA", DCOUNTA.class);
        clazzMap.put("DDB", DDB.class);
        clazzMap.put("DEC2BIN", DEC2BIN.class);
        clazzMap.put("DEC2HEX", DEC2HEX.class);
        clazzMap.put("DEC2OCT", DEC2OCT.class);
        clazzMap.put("DEGREES", DEGREES.class);
        clazzMap.put("DELTA", DELTA.class);
        clazzMap.put("DEVSQ", DEVSQ.class);
        clazzMap.put("DGET", DGET.class);
        clazzMap.put("DISC", DISC.class);
        clazzMap.put("DMAX", DMAX.class);
        clazzMap.put("DMIN", DMIN.class);
        clazzMap.put("DOLLAR", DOLLAR.class);
        clazzMap.put("DOLLARDE", DOLLARDE.class);
        clazzMap.put("DOLLARFR", DOLLARFR.class);
        clazzMap.put("DPRODUCT", DPRODUCT.class);
        clazzMap.put("DSTDEV", DSTDEV.class);
        clazzMap.put("DSTDEVP", DSTDEVP.class);
        clazzMap.put("DSUM", DSUM.class);
        clazzMap.put("DURATION", DURATION.class);
        clazzMap.put("DVAR", DVAR.class);
        clazzMap.put("DVARP", DVARP.class);
        clazzMap.put("EDATEEFFECT", EDATEEFFECT.class);
        clazzMap.put("EOMONTH", EOMONTH.class);
        clazzMap.put("ERF", ERF.class);
        clazzMap.put("ERFC", ERFC.class);
        clazzMap.put("ERROR_TYPE", ERROR_TYPE.class);
        clazzMap.put("EUROCONVERT", EUROCONVERT.class);
        clazzMap.put("EVEN", EVEN.class);
        clazzMap.put("EXACT", EXACT.class);
        clazzMap.put("EXP", EXP.class);
        clazzMap.put("EXPONDIST", EXPONDIST.class);
        clazzMap.put("FACT", FACT.class);
        clazzMap.put("FACTDOUBLE", FACTDOUBLE.class);
        clazzMap.put("FALSE", FALSE.class);
        clazzMap.put("FDIST", FDIST.class);
        clazzMap.put("FIND", FIND.class);
        clazzMap.put("FINV", FINV.class);
        clazzMap.put("FISHER", FISHER.class);
        clazzMap.put("FISHERINV", FISHERINV.class);
        clazzMap.put("FIXED", FIXED.class);
        clazzMap.put("FLOOR", FLOOR.class);
        clazzMap.put("FORECAST", FORECAST.class);
        clazzMap.put("FREQUENCY", FREQUENCY.class);
        clazzMap.put("FTEST", FTEST.class);
        clazzMap.put("FV", FV.class);
        clazzMap.put("FVSCHEDULE", FVSCHEDULE.class);
        clazzMap.put("GAMMADIST", GAMMADIST.class);
        clazzMap.put("GAMMAINV", GAMMAINV.class);
        clazzMap.put("GAMMALN", GAMMALN.class);
        clazzMap.put("GCD", GCD.class);
        clazzMap.put("GEOMEAN", GEOMEAN.class);
        clazzMap.put("GESTEP", GESTEP.class);
        clazzMap.put("GETPIVOTDATA", GETPIVOTDATA.class);
        clazzMap.put("GROWTH", GROWTH.class);
        clazzMap.put("HARMEAN", HARMEAN.class);
        clazzMap.put("HEX2BIN", HEX2BIN.class);
        clazzMap.put("HEX2DEC", HEX2DEC.class);
        clazzMap.put("HEX2OCT", HEX2OCT.class);
        clazzMap.put("HLOOKUP", HLOOKUP.class);
        clazzMap.put("HOUR", HOUR.class);
        clazzMap.put("HYPERLINK", HYPERLINK.class);
        clazzMap.put("HYPGEOMDIST", HYPGEOMDIST.class);
        clazzMap.put("IF", IF.class);
        clazzMap.put("IFERROR", IFERROR.class);
        clazzMap.put("IMABS", IMABS.class);
        clazzMap.put("IMAGINARY", IMAGINARY.class);
        clazzMap.put("IMARGUMENT", IMARGUMENT.class);
        clazzMap.put("IMCONJUGATE", IMCONJUGATE.class);
        clazzMap.put("IMCOS", IMCOS.class);
        clazzMap.put("IMDIV", IMDIV.class);
        clazzMap.put("IMEXP", IMEXP.class);
        clazzMap.put("IMLN", IMLN.class);
        clazzMap.put("IMLOG10", IMLOG10.class);
        clazzMap.put("IMLOG2", IMLOG2.class);
        clazzMap.put("IMPOWER", IMPOWER.class);
        clazzMap.put("IMPRODUCT", IMPRODUCT.class);
        clazzMap.put("IMREAL", IMREAL.class);
        clazzMap.put("IMSIN", IMSIN.class);
        clazzMap.put("IMSQRT", IMSQRT.class);
        clazzMap.put("IMSUB", IMSUB.class);
        clazzMap.put("IMSUM", IMSUM.class);
        clazzMap.put("INDEX", INDEX.class);
        clazzMap.put("INDIRECT", INDIRECT.class);
        clazzMap.put("INFO", INFO.class);
        clazzMap.put("parseINT", INT.class);
        clazzMap.put("INTERCEPT", INTERCEPT.class);
        clazzMap.put("INTRATE", INTRATE.class);
        clazzMap.put("IPMT", IPMT.class);
        clazzMap.put("IRR", IRR.class);
        clazzMap.put("IS", IS.class);
        clazzMap.put("ISB", ISB.class);
        clazzMap.put("ISBLANK", ISBLANK.class);
        clazzMap.put("ISERROR", ISERROR.class);
        clazzMap.put("ISNA", ISNA.class);
        clazzMap.put("ISNUMBER", ISNUMBER.class);
        clazzMap.put("ISPMT", ISPMT.class);
        clazzMap.put("JIS", JIS.class);
        clazzMap.put("KURT", KURT.class);
        clazzMap.put("LARGE", LARGE.class);
        clazzMap.put("LCM", LCM.class);
        clazzMap.put("LEFT", LEFT.class);
        clazzMap.put("LEFTB", LEFTB.class);
        clazzMap.put("LEN", LEN.class);
        clazzMap.put("LENB", LENB.class);
        clazzMap.put("LINEST", LINEST.class);
        clazzMap.put("LN", LN.class);
        clazzMap.put("LOG", LOG.class);
        clazzMap.put("LOG10", LOG10.class);
        clazzMap.put("LOGEST", LOGEST.class);
        clazzMap.put("LOGINV", LOGINV.class);
        clazzMap.put("LOGNORMDIST", LOGNORMDIST.class);
        clazzMap.put("LOOKUP", LOOKUP.class);
        clazzMap.put("LOWER", LOWER.class);
        clazzMap.put("MATCH", MATCH.class);
        clazzMap.put("MAX", MAX.class);
        clazzMap.put("MAXA", MAXA.class);
        clazzMap.put("MDETERM", MDETERM.class);
        clazzMap.put("MDURATION", MDURATION.class);
        clazzMap.put("MEDIAN", MEDIAN.class);
        clazzMap.put("MID", MID.class);
        clazzMap.put("MIDB", MIDB.class);
        clazzMap.put("MIN", MIN.class);
        clazzMap.put("MINA", MINA.class);
        clazzMap.put("MINUTE", MINUTE.class);
        clazzMap.put("MINVERSE", MINVERSE.class);
        clazzMap.put("MIRR", MIRR.class);
        clazzMap.put("MMULT", MMULT.class);
        clazzMap.put("MOD", MOD.class);
        clazzMap.put("MODE", MODE.class);
        clazzMap.put("MONTH", MONTH.class);
        clazzMap.put("MROUND", MROUND.class);
        clazzMap.put("MULTINOMIAL", MULTINOMIAL.class);
        clazzMap.put("N", N.class);
        clazzMap.put("NA", NA.class);
        clazzMap.put("NEGBINOMDIST", NEGBINOMDIST.class);
        clazzMap.put("NETWORKDAYS", NETWORKDAYS.class);
        clazzMap.put("NOMINAL", NOMINAL.class);
        clazzMap.put("NORMDIST", NORMDIST.class);
        clazzMap.put("NORMINV", NORMINV.class);
        clazzMap.put("NORMSDIST", NORMSDIST.class);
        clazzMap.put("NORMSINV", NORMSINV.class);
        clazzMap.put("NOT", NOT.class);
        clazzMap.put("NOW", NOW.class);
        clazzMap.put("NPER", NPER.class);
        clazzMap.put("NPV", NPV.class);
        clazzMap.put("OCT2BIN", OCT2BIN.class);
        clazzMap.put("OCT2DEC", OCT2DEC.class);
        clazzMap.put("OCT2HEX", OCT2HEX.class);
        clazzMap.put("ODD", ODD.class);
        clazzMap.put("ODDFPRICE", ODDFPRICE.class);
        clazzMap.put("ODDFYIELD", ODDFYIELD.class);
        clazzMap.put("ODDLPRICE", ODDLPRICE.class);
        clazzMap.put("ODDLYIELD", ODDLYIELD.class);
        clazzMap.put("OFFSET", OFFSET.class);
        clazzMap.put("OR", OR.class);
        clazzMap.put("PEARSON", PEARSON.class);
        clazzMap.put("PERCENTILE", PERCENTILE.class);
        clazzMap.put("PERCENTRANK", PERCENTRANK.class);
        clazzMap.put("PERMUT", PERMUT.class);
        clazzMap.put("PHONETIC", PHONETIC.class);
        clazzMap.put("PI", PI.class);
        clazzMap.put("PMT", PMT.class);
        clazzMap.put("POISSON", POISSON.class);
        clazzMap.put("POWER", POWER.class);
        clazzMap.put("PPMT", PPMT.class);
        clazzMap.put("PRICE", PRICE.class);
        clazzMap.put("PRICEDISC", PRICEDISC.class);
        clazzMap.put("PRICEMAT", PRICEMAT.class);
        clazzMap.put("PROB", PROB.class);
        clazzMap.put("PRODUCT", PRODUCT.class);
        clazzMap.put("PROPER", PROPER.class);
        clazzMap.put("PV", PV.class);
        clazzMap.put("QUOTIENT", QUOTIENT.class);
        clazzMap.put("RADIANS", RADIANS.class);
        clazzMap.put("RAND", RAND.class);
        clazzMap.put("RANDBETWEEN", RANDBETWEEN.class);
        clazzMap.put("RANK", RANK.class);
        clazzMap.put("RATE", RATE.class);
        clazzMap.put("RECEIVED", RECEIVED.class);
        clazzMap.put("REGISTER_ID", REGISTER_ID.class);
        clazzMap.put("REPLACE", REPLACE.class);
        clazzMap.put("REPLACEB", REPLACEB.class);
        clazzMap.put("REPT", REPT.class);
        clazzMap.put("RIGHT", RIGHT.class);
        clazzMap.put("RIGHTB", RIGHTB.class);
        clazzMap.put("ROMAN", ROMAN.class);
        clazzMap.put("ROUND", ROUND.class);
        clazzMap.put("ROUNDDOWN", ROUNDDOWN.class);
        clazzMap.put("ROUNDUP", ROUNDUP.class);
        clazzMap.put("ROW", ROW.class);
        clazzMap.put("ROWS", ROWS.class);
        clazzMap.put("RSQ", RSQ.class);
        clazzMap.put("RTD", RTD.class);
        clazzMap.put("SEARCH", SEARCH.class);
        clazzMap.put("SEARCHB", SEARCHB.class);
        clazzMap.put("SECOND", SECOND.class);
        clazzMap.put("SERIESSUM", SERIESSUM.class);
        clazzMap.put("SIGN", SIGN.class);
        clazzMap.put("SIN", SIN.class);
        clazzMap.put("SINH", SINH.class);
        clazzMap.put("SKEW", SKEW.class);
        clazzMap.put("SLN", SLN.class);
        clazzMap.put("SLOPE", SLOPE.class);
        clazzMap.put("SMALL", SMALL.class);
        clazzMap.put("SQL_REQUEST", SQL_REQUEST.class);
        clazzMap.put("SQRT", SQRT.class);
        clazzMap.put("SQRTPI", SQRTPI.class);
        clazzMap.put("STANDARDIZE", STANDARDIZE.class);
        clazzMap.put("STDEV", STDEV.class);
        clazzMap.put("STDEVA", STDEVA.class);
        clazzMap.put("STDEVP", STDEVP.class);
        clazzMap.put("STDEVPA", STDEVPA.class);
        clazzMap.put("STEYX", STEYX.class);
        clazzMap.put("SUBSTITUTE", SUBSTITUTE.class);
        clazzMap.put("SUBTOTAL", SUBTOTAL.class);
        clazzMap.put("SUM", SUM.class);
        clazzMap.put("SUMIF", SUMIF.class);
        clazzMap.put("SUMIFS", SUMIFS.class);
        clazzMap.put("SUMPRODUCT", SUMPRODUCT.class);
        clazzMap.put("SUMSQ", SUMSQ.class);
        clazzMap.put("SUMX2MY2", SUMX2MY2.class);
        clazzMap.put("SUMX2PY2", SUMX2PY2.class);
        clazzMap.put("SUMXMY2", SUMXMY2.class);
        clazzMap.put("SYD", SYD.class);
        clazzMap.put("T", T.class);
        clazzMap.put("TAN", TAN.class);
        clazzMap.put("TANH", TANH.class);
        clazzMap.put("TBILLEQ", TBILLEQ.class);
        clazzMap.put("TBILLPRICE", TBILLPRICE.class);
        clazzMap.put("TBILLYIELD", TBILLYIELD.class);
        clazzMap.put("TDIST", TDIST.class);
        clazzMap.put("TEXT", TEXT.class);
        clazzMap.put("TIME", TIME.class);
        clazzMap.put("TIMEVALUE", TIMEVALUE.class);
        clazzMap.put("TINV", TINV.class);
        clazzMap.put("TODAY", TODAY.class);
        clazzMap.put("TRANSPOSE", TRANSPOSE.class);
        clazzMap.put("TREND", TREND.class);
        clazzMap.put("TRIM", TRIM.class);
        clazzMap.put("TRIMMEAN", TRIMMEAN.class);
        clazzMap.put("TRUE", TRUE.class);
        clazzMap.put("TRUNC", TRUNC.class);
        clazzMap.put("TTEST", TTEST.class);
        clazzMap.put("TYPE", TYPE.class);
        clazzMap.put("UPPER", UPPER.class);
        clazzMap.put("VALUE", VALUE.class);
        clazzMap.put("VAR", VAR.class);
        clazzMap.put("VARA", VARA.class);
        clazzMap.put("VARP", VARP.class);
        clazzMap.put("VARPA", VARPA.class);
        clazzMap.put("VDB", VDB.class);
        clazzMap.put("VLOOKUP", VLOOKUP.class);
        clazzMap.put("WEEKDAY", WEEKDAY.class);
        clazzMap.put("WEEKNUM", WEEKNUM.class);
        clazzMap.put("WEIBULL", WEIBULL.class);
        clazzMap.put("WORKDAY", WORKDAY.class);
        clazzMap.put("XOR", XOR.class);
        clazzMap.put("XIRR", XIRR.class);
        clazzMap.put("XNPV", XNPV.class);
        clazzMap.put("YEAR", YEAR.class);
        clazzMap.put("YEARFRAC", YEARFRAC.class);
        clazzMap.put("YIELD", YIELD.class);
        clazzMap.put("YIELDDISC", YIELDDISC.class);
        clazzMap.put("YIELDMAT", YIELDMAT.class);
        clazzMap.put("ZTEST", ZTEST.class);
    }

    private Start builtInFunction;
    private Formula[] args;

    public BuiltinFactory() {
    }

    public Start getBuiltInFunction() {
        return builtInFunction;
    }

    public Start[] getArgs() {
        return args;
    }

    public void create(int arity, String name) throws UnsupportedBuiltinException {
        Class<?> clazz;
        try {
            clazz = clazzMap.get(name);
            if(clazz==null){
                //try to remove _xlnm. if exists
                name = name.replaceAll("_xlnm.","");
                clazz = clazzMap.get(name);
            }
            if(clazz == null)
                throw new UnsupportedBuiltinException("Unsupported " + name);
            if(arity == 0) {
                Constructor<?> constructor = clazz.getConstructor();
                builtInFunction = (Start) constructor.newInstance();
                return;
            }
            args = new Formula[arity];
            Constructor<?> constructor = clazz.getConstructor(Formula[].class);
            builtInFunction = (Start) constructor.newInstance(new Object[]{args});
        } catch(NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            LOG.log(Level.SEVERE, null, ex);
            throw new UnsupportedBuiltinException(ex.getMessage());
        }
    }

}
