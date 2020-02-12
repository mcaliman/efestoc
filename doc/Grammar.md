# Pseudo BNF Grammar

```
⟨Start⟩ ::= = ⟨Formula⟩ | ⟨ArrayFormula⟩ | ⟨Metadata⟩ 
⟨ArrayFormula⟩ ::= {= ⟨Formula⟩ }
⟨Formula⟩ ::= ⟨Constant⟩ | ⟨Reference⟩ | ⟨FunctionCall⟩ | ⟨ParenthesisFormula⟩ | ⟨ConstantArray⟩ | RESERVED_NAME
⟨ParenthesisFormula⟩ ::= ( ⟨Formula⟩ )
⟨Constant⟩ ::= ⟨Number⟩ | TEXT | BOOL | DATETIME | ERROR  
⟨Number⟩::= INT | FLOAT
⟨FunctionCall⟩ ::=  ⟨EXCEL_FUNCTION⟩ | ⟨Unary⟩ | ⟨PercentFormula⟩ | ⟨Binary⟩
```




## Functions arguments

```
⟨Arguments⟩ ::= ϵ | ⟨Argument⟩ { , ⟨Argument⟩ }
⟨Argument⟩ ::= ⟨Formula⟩ | ϵ
```

Implemented as varargs of Formula

```
EXCEL_FUNCTION(Formula... args)
```

```
⟨Unary⟩ ::=  ⟨Plus⟩ | ⟨Minus⟩ 
⟨Plus⟩  ::= +⟨Formula⟩ 
⟨Minus⟩ ::= -⟨Formula⟩ 
```
```
⟨Binary⟩   ::= ⟨Add⟩ | ⟨Sub⟩ | ⟨Mult⟩ | ⟨Divide⟩ | ⟨Lt⟩ | ⟨Gt⟩ | ⟨Eq⟩ | ⟨Leq⟩ | ⟨GtEq⟩ | ⟨Neq⟩  | ⟨Concat⟩ | ⟨Power⟩
⟨Add⟩      ::= ⟨Formula⟩+⟨Formula⟩
⟨Sub⟩      ::= ⟨Formula⟩-⟨Formula⟩
⟨Mult⟩     ::= ⟨Formula⟩*⟨Formula⟩
⟨Divide⟩   ::= ⟨Formula⟩/⟨Formula⟩
⟨Lt⟩       ::= ⟨Formula⟩<⟨Formula⟩
⟨Gt⟩       ::= ⟨Formula⟩>⟨Formula⟩
⟨Eq⟩       ::= ⟨Formula⟩=⟨Formula⟩
⟨Leq⟩      ::= ⟨Formula⟩<=⟨Formula⟩
⟨GtEq⟩     ::= ⟨Formula⟩>=⟨Formula⟩
⟨Neq⟩      ::= ⟨Formula⟩<>⟨Formula⟩
⟨Concat⟩   ::= ⟨Formula⟩&⟨Formula⟩
⟨Power⟩    ::= ⟨Formula⟩^⟨Formula⟩
```

Traduzione in Clojure




```
⟨PercentFormula⟩ ::= ⟨Formula⟩%
```

```
⟨Reference⟩ ::= 
⟨ReferenceItem⟩
| ⟨RangeReference⟩
| ⟨Intersection⟩ 
| ( ⟨Union⟩ )
| ( ⟨Reference⟩ )
| ⟨PrefixReferenceItem⟩
| ⟨Prefix⟩ UDF* ⟨Arguments⟩ ) (notImp.)  
| ⟨DynamicDataExchange⟩(notImp.)
```

```
⟨RangeReference⟩ ::= RANGE
RANGE::=⟨Reference⟩ : ⟨Reference⟩ 
⟨Intersection⟩# ::= ⟨Reference⟩ ' ' ⟨Reference⟩       //Implemented as "Binary"
⟨Union⟩ ::= ⟨Reference⟩# | ⟨Reference⟩ , ⟨Union⟩     //Implemented as "Binary"
```

```
⟨PrefixReferenceItem⟩# ::= ⟨Prefix⟩ ⟨ReferenceItem⟩  
```

```
⟨ReferenceItem⟩ ::= CELL_REFERENCE
| ⟨NamedRange⟩
//| ⟨StructuredReference⟩
| 
//| VERTICAL_RANGE
//| HORIZONTAL_RANGE
| UDF(⟨Arguments⟩) | ERROR_REF | ⟨REFERENCE_FUNCTION⟩ | ⟨REF_FUNCTION_COND⟩  
```

```  
 ⟨REFERENCE_FUNCTION⟩ ::= INDEX(⟨Arguments⟩) | OFFSET(⟨Arguments⟩) | INDIRECT(⟨Arguments⟩)  
 ⟨REF_FUNCTION_COND⟩  ::= IF(⟨Arguments⟩) | CHOOSE(⟨Arguments⟩) //Not.Correctly implemented, inherits from Function (not ReferenceItem) 
```

``` 
⟨NamedRange⟩ ::= ⟨Name⟩
⟨Name⟩ ::= NAME | NAME_PREFIXED 
```

``` 
⟨Prefix⟩ ::= SHEET | 'SHEET_QUOTED | ⟨File⟩SHEET | '⟨File⟩SHEET_QUOTED | FILE! | MULTIPLE_SHEETS | ⟨File⟩MULTIPLE_SHEETS
⟨File⟩   ::= FILE  | FILENAME | FILEPATH FILENAME
```

## Excel built-in function

```
⟨EXCEL_FUNCTION⟩ ::= 
    ABS(⟨Arguments⟩) | 
    ACCRINT(⟨Arguments⟩) | 
    ACCRINTM(⟨Arguments⟩) | 
    ACOS(⟨Arguments⟩)    | 
    ACOSH(⟨Arguments⟩) | 
    ADDRESS(⟨Arguments⟩) | 
    AMORDEGRC(⟨Arguments⟩) | 
    AMORLINC(⟨Arguments⟩) | 
    AND(⟨Arguments⟩) | 
    AREAS(⟨Arguments⟩) | 
    ASC(⟨Arguments⟩) | 
    ASIN(⟨Arguments⟩) | 
    ASINH(⟨Arguments⟩) | 
    ATAN(⟨Arguments⟩) | 
    ATAN2(⟨Arguments⟩) | 
    ATANH(⟨Arguments⟩) | 
    AVEDEV(⟨Arguments⟩) | 
    AVERAGE(⟨Arguments⟩) | 
    AVERAGEA(⟨Arguments⟩) | 
    AVERAGEIF(⟨Arguments⟩) | 
    AVERAGEIFS(⟨Arguments⟩) | 
    BAHTTEXT(⟨Arguments⟩) | 
    BESSELI(⟨Arguments⟩) | 
    BESSELJ(⟨Arguments⟩) | 
    BESSELK(⟨Arguments⟩) | 
    BESSELY(⟨Arguments⟩) | 
    BETADIST(⟨Arguments⟩) | 
    BETAINV(⟨Arguments⟩) | 
    BIN2DEC(⟨Arguments⟩) | 
    BIN2HEX(⟨Arguments⟩) | 
    BIN2OCT(⟨Arguments⟩) | 
    BINOMDIST(⟨Arguments⟩) | 
    CALL(⟨Arguments⟩) | 
    CEILING(⟨Arguments⟩) | 
    CELL(⟨Arguments⟩) | 
    CHAR(⟨Arguments⟩) | 
    CHIDIST(⟨Arguments⟩) | 
    CHIINV(⟨Arguments⟩) | 
    CHITEST(⟨Arguments⟩) | 
    CLEAN(⟨Arguments⟩) | 
    CODE(⟨Arguments⟩) | 
    COLUMN(⟨Arguments⟩) | 
    COLUMNS(⟨Arguments⟩) | 
    COMBIN(⟨Arguments⟩) | 
    COMPLEX(⟨Arguments⟩) | 
    CONCATENATE(⟨Arguments⟩) | 
    CONFIDENCE(⟨Arguments⟩) | 
    CONVERT(⟨Arguments⟩) | 
    CORREL(⟨Arguments⟩) | 
    COS(⟨Arguments⟩) | 
    COSH(⟨Arguments⟩) | 
    COUNT(⟨Arguments⟩) | 
    COUNTA(⟨Arguments⟩) | 
    COUNTBLANK(⟨Arguments⟩) | 
    COUNTIF(⟨Arguments⟩) | 
    COUNTIFS(⟨Arguments⟩) | 
    COUPDAYBS(⟨Arguments⟩) | 
    COUPDAYS(⟨Arguments⟩) | 
    COUPDAYSNC(⟨Arguments⟩) | 
    COUPNCD(⟨Arguments⟩) | 
    COUPNUM(⟨Arguments⟩) | 
    COUPPCD(⟨Arguments⟩) | 
    COVAR(⟨Arguments⟩) | 
    CRITBINOM(⟨Arguments⟩) | 
    CUBEKPIMEMBER(⟨Arguments⟩) | 
    CUBEMEMBER(⟨Arguments⟩) | 
    CUBEMEMBERPROPERTY(⟨Arguments⟩) | 
    CUBERANKEDMEMBER(⟨Arguments⟩) | 
    CUBESET(⟨Arguments⟩) | 
    CUBESETCOUNT(⟨Arguments⟩) | 
    CUBEVALUE(⟨Arguments⟩) | 
    CUMIPMT(⟨Arguments⟩) | 
    CUMPRINC(⟨Arguments⟩) | 
    DATE(⟨Arguments⟩) | 
    DATEVALUE(⟨Arguments⟩) | 
    DAVERAGE(⟨Arguments⟩) | 
    DAY(⟨Arguments⟩) | 
    DAYS360(⟨Arguments⟩) | 
    DB(⟨Arguments⟩) | 
    DCOUNT(⟨Arguments⟩) | 
    DCOUNTA(⟨Arguments⟩) | 
    DDB(⟨Arguments⟩) | 
    DEC2BIN(⟨Arguments⟩) | 
    DEC2HEX(⟨Arguments⟩) | 
    DEC2OCT(⟨Arguments⟩) | 
    DEGREES(⟨Arguments⟩) | 
    DELTA(⟨Arguments⟩) | 
    DEVSQ(⟨Arguments⟩) | 
    DGET(⟨Arguments⟩) | 
    DISC(⟨Arguments⟩) | 
    DMAX(⟨Arguments⟩) | 
    DMIN(⟨Arguments⟩) | 
    DOLLAR(⟨Arguments⟩) | 
    DOLLARDE(⟨Arguments⟩) | 
    DOLLARFR(⟨Arguments⟩) | 
    DPRODUCT(⟨Arguments⟩) | 
    DSTDEV(⟨Arguments⟩) | 
    DSTDEVP(⟨Arguments⟩) | 
    DSUM(⟨Arguments⟩) | 
    DURATION(⟨Arguments⟩) | 
    DVAR(⟨Arguments⟩) | 
    DVARP(⟨Arguments⟩) | 
    EDATEEFFECT(⟨Arguments⟩) | 
    EOMONTH(⟨Arguments⟩) | 
    ERF(⟨Arguments⟩) | 
    ERFC(⟨Arguments⟩) | 
    ERROR_TYPE(⟨Arguments⟩) | 
    EUROCONVERT(⟨Arguments⟩) | 
    EVEN(⟨Arguments⟩) | 
    EXACT(⟨Arguments⟩) | 
    EXP(⟨Arguments⟩) | 
    EXPONDIST(⟨Arguments⟩) | 
    FACT(⟨Arguments⟩) | 
    FACTDOUBLE(⟨Arguments⟩) | 
    FALSE(⟨Arguments⟩) | 
    FDIST(⟨Arguments⟩) | 
    FIND(⟨Arguments⟩) | 
    FINV(⟨Arguments⟩) | 
    FISHER(⟨Arguments⟩) | 
    FISHERINV(⟨Arguments⟩) | 
    FIXED(⟨Arguments⟩) | 
    FLOOR(⟨Arguments⟩) | 
    FORECAST(⟨Arguments⟩) | 
    FREQUENCY(⟨Arguments⟩) | 
    FTEST(⟨Arguments⟩) | 
    FV(⟨Arguments⟩) | 
    FVSCHEDULE(⟨Arguments⟩) | 
    GAMMADIST(⟨Arguments⟩) | 
    GAMMAINV(⟨Arguments⟩) | 
    GAMMALN(⟨Arguments⟩) | 
    GCD(⟨Arguments⟩) | 
    GEOMEAN(⟨Arguments⟩) | 
    GESTEP(⟨Arguments⟩) | 
    GETPIVOTDATA(⟨Arguments⟩) | 
    GROWTH(⟨Arguments⟩) | 
    HARMEAN(⟨Arguments⟩) | 
    HEX2BIN(⟨Arguments⟩) | 
    HEX2DEC(⟨Arguments⟩) | 
    HEX2OCT(⟨Arguments⟩) | 
    HLOOKUP(⟨Arguments⟩) | 
    HOUR(⟨Arguments⟩) | 
    HYPERLINK(⟨Arguments⟩) | 
    HYPGEOMDIST(⟨Arguments⟩) | 
    IFERROR(⟨Arguments⟩) | 
    IMABS(⟨Arguments⟩) | 
    IMAGINARY(⟨Arguments⟩) | 
    IMARGUMENT(⟨Arguments⟩) | 
    IMCONJUGATE(⟨Arguments⟩) | 
    IMCOS(⟨Arguments⟩) | 
    IMDIV(⟨Arguments⟩) | 
    IMEXP(⟨Arguments⟩) | 
    IMLN(⟨Arguments⟩) | 
    IMLOG10(⟨Arguments⟩) | 
    IMLOG2(⟨Arguments⟩) | 
    IMPOWER(⟨Arguments⟩) | 
    IMPRODUCT(⟨Arguments⟩) | 
    IMREAL(⟨Arguments⟩) | 
    IMSIN(⟨Arguments⟩) | 
    IMSQRT(⟨Arguments⟩) | 
    IMSUB(⟨Arguments⟩) | 
    IMSUM(⟨Arguments⟩) | 
    INFO(⟨Arguments⟩) | 
    INT(⟨Arguments⟩) | 
    INTERCEPT(⟨Arguments⟩) | 
    INTRATE(⟨Arguments⟩) | 
    IPMT(⟨Arguments⟩) | 
    IRR(⟨Arguments⟩) | 
    IS(⟨Arguments⟩) | 
    ISB(⟨Arguments⟩) | 
    ISBLANK(⟨Arguments⟩) | 
    ISERROR(⟨Arguments⟩) | 
    ISNA(⟨Arguments⟩) | 
    ISNUMBER(⟨Arguments⟩) | 
    ISPMT(⟨Arguments⟩) | 
    JIS(⟨Arguments⟩) | 
    KURT(⟨Arguments⟩) | 
    LARGE(⟨Arguments⟩) | 
    LCM(⟨Arguments⟩) |
    LEFT(⟨Arguments⟩) | 
    LEFTB(⟨Arguments⟩) | 
    LEN(⟨Arguments⟩) | 
    LENB(⟨Arguments⟩) | 
    LINEST(⟨Arguments⟩) | 
    LN(⟨Arguments⟩) | 
    LOG(⟨Arguments⟩) | 
    LOG10(⟨Arguments⟩) | 
    LOGEST(⟨Arguments⟩) | 
    LOGINV(⟨Arguments⟩) | 
    LOGNORMDIST(⟨Arguments⟩) | 
    LOOKUP(⟨Arguments⟩) | 
    LOWER(⟨Arguments⟩) | 
    MATCH(⟨Arguments⟩) | 
    MAX(⟨Arguments⟩) | 
    MAXA(⟨Arguments⟩) | 
    MDETERM(⟨Arguments⟩) | 
    MDURATION(⟨Arguments⟩) | 
    MEDIAN(⟨Arguments⟩) | 
    MID(⟨Arguments⟩) | 
    MIDB(⟨Arguments⟩) | 
    MIN(⟨Arguments⟩) | 
    MINA(⟨Arguments⟩) | 
    MINUTE(⟨Arguments⟩) | 
    MINVERSE(⟨Arguments⟩) | 
    MIRR(⟨Arguments⟩) | 
    MMULT(⟨Arguments⟩) | 
    MOD(⟨Arguments⟩) | 
    MODE(⟨Arguments⟩) | 
    MONTH(⟨Arguments⟩) | 
    MROUND(⟨Arguments⟩) | 
    MULTINOMIAL(⟨Arguments⟩) | 
    N(⟨Arguments⟩) | 
    NA(⟨Arguments⟩) | 
    NEGBINOMDIST(⟨Arguments⟩) | 
    NETWORKDAYS(⟨Arguments⟩) | 
    NOMINAL(⟨Arguments⟩) | 
    NORMDIST(⟨Arguments⟩) | 
    NORMINV(⟨Arguments⟩) | 
    NORMSDIST(⟨Arguments⟩) | 
    NORMSINV(⟨Arguments⟩) | 
    NOT(⟨Arguments⟩) | 
    NOW(⟨Arguments⟩) | 
    NPER(⟨Arguments⟩) | 
    NPV(⟨Arguments⟩) | 
    OCT2BIN(⟨Arguments⟩) | 
    OCT2DEC(⟨Arguments⟩) | 
    OCT2HEX(⟨Arguments⟩) | 
    ODD(⟨Arguments⟩) | 
    ODDFPRICE(⟨Arguments⟩) | 
    ODDFYIELD(⟨Arguments⟩) | 
    ODDLPRICE(⟨Arguments⟩) | 
    ODDLYIELD(⟨Arguments⟩) | 
    OR(⟨Arguments⟩) | 
    PEARSON(⟨Arguments⟩) | 
    PERCENTILE(⟨Arguments⟩) | 
    PERCENTRANK(⟨Arguments⟩) | 
    PERMUT(⟨Arguments⟩) | 
    PHONETIC(⟨Arguments⟩) | 
    PI(⟨Arguments⟩) | 
    PMT(⟨Arguments⟩) | 
    POISSON(⟨Arguments⟩) | 
    POWER(⟨Arguments⟩) | 
    PPMT(⟨Arguments⟩) | 
    PRICE(⟨Arguments⟩) | 
    PRICEDISC(⟨Arguments⟩) | 
    PRICEMAT(⟨Arguments⟩) | 
    PROB(⟨Arguments⟩) | 
    PRODUCT(⟨Arguments⟩) | 
    PROPER(⟨Arguments⟩) | 
    PV(⟨Arguments⟩) | 
    QUOTIENT(⟨Arguments⟩) | 
    RADIANS(⟨Arguments⟩) | 
    RAND(⟨Arguments⟩) | 
    RANDBETWEEN(⟨Arguments⟩) | 
    RANK(⟨Arguments⟩) | 
    RATE(⟨Arguments⟩) | 
    RECEIVED(⟨Arguments⟩) | 
    REGISTER_ID(⟨Arguments⟩) | 
    REPLACE(⟨Arguments⟩) | 
    REPLACEB(⟨Arguments⟩) | 
    REPT(⟨Arguments⟩) | 
    RIGHT(⟨Arguments⟩) | 
    RIGHTB(⟨Arguments⟩) | 
    ROMAN(⟨Arguments⟩) | 
    ROUND(⟨Arguments⟩) | 
    ROUNDDOWN(⟨Arguments⟩) | 
    ROUNDUP(⟨Arguments⟩) | 
    ROW(⟨Arguments⟩) | 
    ROWS(⟨Arguments⟩) | 
    RSQ(⟨Arguments⟩) | 
    RTD(⟨Arguments⟩) | 
    SEARCH(⟨Arguments⟩) | 
    SEARCHB(⟨Arguments⟩) | 
    SECOND(⟨Arguments⟩) | 
    SERIESSUM(⟨Arguments⟩) | 
    SIGN(⟨Arguments⟩) | 
    SIN(⟨Arguments⟩) | 
    SINH(⟨Arguments⟩) | 
    SKEW(⟨Arguments⟩) | 
    SLN(⟨Arguments⟩) | 
    SLOPE(⟨Arguments⟩) | 
    SMALL(⟨Arguments⟩) | 
    SQL_REQUEST(⟨Arguments⟩) | 
    SQRT(⟨Arguments⟩) | 
    SQRTPI(⟨Arguments⟩) | 
    STANDARDIZE(⟨Arguments⟩) | 
    STDEV(⟨Arguments⟩) | 
    STDEVA(⟨Arguments⟩) | 
    STDEVP(⟨Arguments⟩) | 
    STDEVPA(⟨Arguments⟩) | 
    STEYX(⟨Arguments⟩) | 
    SUBSTITUTE(⟨Arguments⟩) | 
    SUBTOTAL(⟨Arguments⟩) | 
    SUM(⟨Arguments⟩) | 
    SUMIF(⟨Arguments⟩) | 
    SUMIFS(⟨Arguments⟩) | 
    SUMPRODUCT(⟨Arguments⟩) | 
    SUMSQ(⟨Arguments⟩) | 
    SUMX2MY2(⟨Arguments⟩) | 
    SUMX2PY2(⟨Arguments⟩) | 
    SUMXMY2(⟨Arguments⟩) | 
    SYD(⟨Arguments⟩) | 
    T(⟨Arguments⟩) | 
    TAN(⟨Arguments⟩) | 
    TANH(⟨Arguments⟩) | 
    TBILLEQ(⟨Arguments⟩) | 
    TBILLPRICE(⟨Arguments⟩) | 
    TBILLYIELD(⟨Arguments⟩) | 
    TDIST(⟨Arguments⟩) |
    TEXT(⟨Arguments⟩) | 
    TIME(⟨Arguments⟩) | 
    TIMEVALUE(⟨Arguments⟩) | 
    TINV(⟨Arguments⟩) | 
    TODAY(⟨Arguments⟩) | 
    TRANSPOSE(⟨Arguments⟩) | 
    TREND(⟨Arguments⟩) | 
    TRIM(⟨Arguments⟩) | 
    TRIMMEAN(⟨Arguments⟩) | 
    TRUE(⟨Arguments⟩) | 
    TRUNC(⟨Arguments⟩) | 
    TTEST(⟨Arguments⟩) | 
    TYPE(⟨Arguments⟩) | 
    UPPER(⟨Arguments⟩) | 
    VALUE(⟨Arguments⟩) | 
    VAR(⟨Arguments⟩) | 
    VARA(⟨Arguments⟩) | 
    VARP(⟨Arguments⟩) | 
    VARPA(⟨Arguments⟩) | 
    VDB(⟨Arguments⟩) | 
    VLOOKUP(⟨Arguments⟩) | 
    WEEKDAY(⟨Arguments⟩) | 
    WEEKNUM(⟨Arguments⟩) | 
    WEIBULL(⟨Arguments⟩) | 
    WORKDAY(⟨Arguments⟩) | 
    XIRR(⟨Arguments⟩) | 
    XNPV(⟨Arguments⟩) | 
    YEAR(⟨Arguments⟩) | 
    YEARFRAC(⟨Arguments⟩) | 
    YIELD(⟨Arguments⟩) | 
    YIELDDISC(⟨Arguments⟩) | 
    YIELDMAT(⟨Arguments⟩) | 
    ZTEST(⟨Arguments⟩)  
```


