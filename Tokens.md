# Tokens 

| Token                  | Description                          |
| :---                   | :---                                 |              
| BOOL                   | Boolean literal                      |
| CELL_REFERENCE         | Cell reference                       |
| DDECALL                | Dynamic Data Exchange link           | 
| ERROR                  | Error literal                        | 
| ERROR_REF              | Reference error literal              | 
| EXCEL_FUNCTION         | Excel built-in function              | 
| FILE                   | External file reference using number | 
| FILENAME               | External file reference using name   | 
| FILEPATH               | Windows file path                    | 
| HORIZONTAL_RANGE       | Range of rows                        | 
| MULTIPLE_SHEETS        | Multiple sheet references            |  
| NAME                   | User Defined Name                    |  
| NAME_PREFIXED          | User defined name which starts with a string that could be another token  |  
| NUMBER                 | An integer, floating point or scientific notation number literal |  
| REF_FUNCTION           | Excel built-in reference function  |
| REF_FUNCTION_COND      | Excel built-in conditional reference function  |  
| RESERVED_NAME          | An Excel reserved name  |  
| SHEET                  | The name of a worksheet  |  
| SHEET_QUOTED           | Quoted worksheet name    |    
| TEXT                   | String literal |    
| SR_COLUMN              | Structured reference column  |    
| UDF                    | User Defined Function |    
| VERTICAL_RANGE         | Range of columns |    

* BOOL ::=  TRUE | FALSE 
* CELL_REFERENCE ::= REGEXP $? [A-Z]+ $? [0-9]+ 
* DDECALL ::= REGEXP ' ([^ '] | ")+ '
* ERROR ::= '#NULL!' | '#DIV/0!' | '#VALUE!' | '#NAME?' | '#NUM!' | '#N/A' 
* ERROR_REF ::= '#REF!'
* EXCEL_FUNCTION ::=  Any entry from the function list
* FILE ::= REGEXP \[ [0-9]+ \] 
* FILENAME ::= \[ RegExp_4+ \] 
* FILEPATH ::= REGEXP [A-Z] : \\ (RegExp_4+ \\)* 
* HORIZONTAL_RANGE ::= REGEXP $? [0-9]+ : $? [0-9]+ 
* MULTIPLE_SHEETS ::= REGEXP ((RegExp_2+ : RegExp_2+)|( ' (RegExp_3 | ")+ : (RegExp_3 | ")+ '' )) ! 
* NAME ::= REGEXP [A-Z_\\][A-Z0-9\\_.RegExp_1]* 
* NAME_PREFIXED ::= REGEXP (TRUE | FALSE | [A-Z]+[0-9]+) [A-Z0-9_.RegExp_1]+ 
* NUMBER ::= REGEXP [0-9]+ ,? [0-9]* (e [0-9]+)? 
* INT::= REGEXP [0-9]+
* FLOAT ::= REGEXP [0-9]+ ,? [0-9]* (e [0-9]+)?  
* REF_FUNCTION ::= INDEX | OFFSET | INDIRECT 
* REF_FUNCTION_COND ::= IF | CHOOSE
* RESERVED_NAME ::= REGEXP _xlnm\. [A-Z_]+ 
* SHEET ::= RegExp_2+ ! 
* SHEET_QUOTED ::= RegExp_3+ ' ! 
* TEXT ::= " ([^ "] | "")* " 
* SR_COLUMN ::= REGEXP \[ [A-Z0-9\\_.RegExp_1]+ \] 
* UDF ::= REGEXP (_xll\.)? [A-Z_\][A-Z0-9_\\.RegExp_1]*  
* VERTICAL_RANGE ::= REGEXP $? [A-Z]+ : $? [A-Z]+ 
    
# Placeholder character 

Placeholder for Specification

* RegExp_1 : Extended characters Non-control Unicode characters x80 and up
* RegExp_2 : Sheet characters Any character except ' * [ ] \ : / ? ( ) ; { } # " = < > & + - * / ^ % , ␣
* RegExp_3 : Enclosed sheet characters Any character except ' * [ ] \ : / ?
* RegExp_4 : Filename characters Any character except " * [ ] \ : / ? < > |    