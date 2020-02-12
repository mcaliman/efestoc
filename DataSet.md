
# Frequency of spreadsheet formulas with specific grammatical structures in the EUSES and Enron datasets

Formula.xlsx <Formula> = 1+2


* Reference = E9/E10
* CELL = E8 
* FunctionCall = SUM(F1:F4) 
* BinOp = F1-F4 
* Function = SUM(F1:F4) 
* FUNCTION =SUM(F1:F4) 
* Constant =SUM(F1:F4) 
* NUMBER =(F4/F2)*15 
* Prefix =Sheet1!B1 
* SHEET =Sheet1!B1 

* Reference:Reference =SUM(A5:A22) 
* UnOpPrefix =+B11+1 
* STRING = COUNTIF(B$4:B$46,">=90") 
* NamedRange = SUM(freq) 
* BOOL =IF(AND(R11=1,R14=TRUE),G19,0) 
* FILE =[11]Sheet1!C5 
* REFERENCE-FUNCTION =SUM(J9:INDEX(J9:J41,B43)) 
* QUOTED-FILE-SHEET =('[2]Detail I&E'!D62)/1000 
* UDF =SQRT(_eoq2(C5,C4,C6,C7)) 
* '_xll.' =_xll.RiskTriang(F9,F7,F8) 
* ERROR_REF =AVERAGE(#REF!) 
* (Reference) =(2*(B29))/(1+B29) 
* VERTICAL-RANGE =COUNT(A:A) 
* FILE! =[1]!today 
* ERROR =IF(AND(R11=1,R14=TRUE),G19,0) 
* '%' =IF(E5>I8,3%,0%) 
* Empty argument =DCOUNT(Lettergrades„I80:I81) 
* Complex range =SUM(I8:K8:M8)
* DynamicDataExchange =TWINDDE|RSFRec!'NGH2 NET.CHNG" 
* Intersection =Ending_Inventory Jan
* MULTIPLE-SHEETS =SUM(Sheet1:Sheet20!I29) 
* Prefixed right reference limit =SUM('Tot-1'!$B8:'Tot-1'!B8) 
* RESERVED_NAME =C23/_xlnm.Print_Area
* UDF reference =[1]!wbname() 
* HORIZONTAL-RANGE =MATCH(F3,Prices!2:2,0) 
* Union =LARGE((F38,C38),1)
* ConstantArray =FVSCHEDULE(1,0.09;0.11;0.1)