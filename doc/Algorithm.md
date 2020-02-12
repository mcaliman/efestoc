# Algorithm


TODO ⟨ConstantArray⟩ 
ConstantArray ::= { ArrayColumns }
ArrayColumns ::= ArrayRows | ArrayRows ; ArrayColumns
ArrayRows ::= ArrayConstant ArrayConstant , ArrayRows
ArrayConstant ::= Constant | UnOpPrefix NUMBER | ERROR-REF
e.g. `Foglio1!A6 = {1;2;2;4;6}` --> `(def Foglio1!A6 [1.0 2.0 2.0 4.0 6.0])`


### Constant
⟨Constant⟩ ::= ⟨Number⟩ | `TEXT` | `BOOL` | `DATETIME` | `ERROR`  
⟨Number⟩::= `INT` | `FLOAT`

INT is not used, all Numbers are FLOAT (e.g. 10 --> 10.0 where --> is a relation)

assume 
    C is a cell address (e.g. A15), float is a float value 
so
    `C = float` --> `(def C float)` 
in example
    `A1 = 10.0` --> `(def A1 10.0)`
    
for TEXT terminal /  lexical token
`C = "Text"` --> `(def C "Text")`

for BOOL values
`C = boolean` --> `(def C Boolean/boolean)`
e.g.
`C = TRUE` --> `(def C Boolean/TRUE)` 

for DATETIME values
C = datetime (in Excel date time value is implemented as numbers)  so we can detect this format property and 
use a clojure macro to convert as Clojure/Java Date/Time/LocalDateTime value or LocalDate Java Objects
`C = datetime` --> `(def C (java.time.LocalDate/parse "2018-02-01"))`

for ERROR type
C = #err 
where 
    C is cell address like A15 and #err is ERROR like #REF      
`C = #err` --> `(def C #err)`

---
### Binary Operation
TODO


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

for the subset of binary operation
operator = + | - | * | / | < | > | <= | >= | = 
assume `C = A op B` --> `(def C (op A B))`

case operator = <> (NotEq)
`C = A <> B` --> `(def C (not= A B))`

for the operator Power ^ 
`C = A ^ B` --> `(def C (Math/pow A B))`

case operator Concat &
`C = A & B` --> `(def C (str A B))`
 
---
### Range and Cell Reference
for range like U:V where U,V are cell address like A1:A3

assume A1:A4 is a range (A1,A2,A3)
where A1:A4 is 10,20,30

`A1:A4` --> `(def A1:A4 [10 20 30])`
so we can refer to the the range as A1:A4 (in Clojure A1:A4 is a legal name, Sheet1!A1:4 is legal name too) 

similarly for area range (matrix) [ [] [] [] ]

### SUM function for range

`C = SUM (U:V)` --> `(def C (reduce + U:V))`

 

---
### Conditional Reference Functions (IF and CHOOSE functions)
TODO

for the IF function: IF ::= IF(B,T,E)
where B is boolean expression, T the 'Then' value if B is true, E the 'Else' value if B is false.
`IF(B,T,E)` --> `(if B T E)`
e.g.
`A1 = IF(B1,T1,E1)` --> `(def A1 (if B1 T1 E1))`

for the CHOOSE function: CHOOSE ::= CHOOSE(index,value1,...valueN)
e.g. CHOOSE(2,"red","blue","yellow") return "blue"


for simplicity in this case we use a macro excel-choose

the macro is thus defined TODO

--> `(choose index  v1 v2 &val)`

---
### Reference Functions (INDEX,INDIRECT and OFFESET functions)
TODO


