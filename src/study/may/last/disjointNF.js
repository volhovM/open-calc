/**
 * @author volhovm
 * Created on 5/27/14
 */

///////////////////////////////////////////////////////////////////Library
var Const = function(c){
    this.c = c;
}

Const.prototype.evaluate = function() {
    if (this.c == '1' || this.c == 1) return true;
    else if (this.c == '0' || this.c == 0) return false;
    else return "ERROR JS HAS NO EXCEPTIONS";
 }
Const.prototype.negate = function() { return ~this.c; }
Const.prototype.toString = function() { return this.c.toString(); }

var FALSE = new Const(0);
var TRUE = new Const(1);


var Variable = function(c){
    this.c = c;
}

Variable.prototype.evaluate = function(varList, arr) {
    return arr[varList.indexOf(this.c)];
}
Variable.prototype.negate = function() { return new Not(this); }
Variable.prototype.toString = function() { return this.c.toString(); }


function makeBOp(charSign, evalFoo) {
    var ret = function(a, b){
        this.a = a;
        this.b = b;
    };
    ret.prototype = {
        constructor: ret,
        charSign: charSign,
        negate: function(){ return new Not(this); },
        evaluate: function(varList) { return evalFoo(this.a.evaluate.apply(this.a, arguments), this.b.evaluate.apply(this.b, arguments)); },
        toString: function() { return "(" + this.a.toString() + charSign + this.b.toString() + ")"; }
    }
    return ret;
}

var And = makeBOp(" & ", function(x, y) { return x && y; });
var Or = makeBOp(" | ", function(x, y) { return x || y; });

var Not = function(c){
    this.c = c;
}
Not.prototype.evaluate = function() {
    var a = this.c.evaluate.apply(this.c, arguments);
    return a == false ? true : false;
}
Not.prototype.negate = function() { return this.c ;}
Not.prototype.toString = function() { return "(~" + this.c.toString() + ")"; }

//EVALUATE METHOD TAKES STRING OF CHARACTERS THAT ARE INSIDE THE EXPRESSION AND THEIR VALUES  IN ARRAY(2 ELEMENTS TOTALLY)
//print(new Not(new Or(new Variable("a"), new Variable("b"))).evaluate("ab", [false, false]));






///////////////////////////////////////////////////////////////////////////// Functional part

function dumpArray(arr){
    var out = "[";
    for (var i = 0; i < arr.length; i++){
        out += arr[i] + " "
    }
    out += "]"
    print(out);
}

function dnf(str) {
    var varList = extractVariables(str);
    var expr = parse(str);
    var stringOut = "";
    var inArray = [];
    for (var i = 0; i < varList.length; i++){
        inArray.push(false);
    }
    var counter = true;
    for (var z = 0; z < (1 << varList.length); z++){
        if (expr.evaluate(varList, inArray) == true){
            stringOut += stringOut == "" ? "(" + mkString(inArray, varList) + ")" : " | (" + mkString(inArray, varList) + ")"
        } else counter = false
        inArray = arrNext(inArray);
    }
    if (counter) return "1"
    return stringOut == "" ? expr.toString() + " couldn't be reduced or it's 0." : stringOut;
}

//combines all varlist[i] via "&" if arr[i] == true
function mkString(arr, varList) {
    var out = "";
    for (var i = 0; i < arr.length - 1; i++){
        if (arr[i]) out += varList[i];
        else out += "~" + varList[i];
        out += " & ";
    }
    if (arr[arr.length - 1]) out += varList[arr.length - 1];
    else out += "~" + varList[arr.length - 1];
    return out;
}

//generates next vector
function arrNext(arr){
    var revPlace = arr.length - 1;
    for (var z = arr.length - 1; z >= 0; z--) {
        if (arr[z] == false) {
            arr[z] = true;
            revPlace = z;
            break;
        }
    }
    for (var i = revPlace + 1; i < arr.length; i++) arr[i] = false;
    return arr;
}

function isAlphabetic(char){
    var varList = "abcdefghjklmnopqrstuvwxyz";
    for (var i = 0; i < varList.length; i++) if (char == varList[i]) return true;
    return false;
}

//makes a sorted list of variables in expression
function extractVariables(str) {
    var arr = [];
    for (var i = 0; i < str.length; i++){
        var cr = str.charAt(i)
        if (isAlphabetic(cr)) arr.push(cr);
    }
    arr.sort();
    for (var i = 1; i < arr.length; i++) if (arr[i] == arr[i - 1]) arr = arr.splice(i, 1);
    arr.sort();
    var strOut = "";
    for (var i = 0; i < arr.length; i++) strOut += arr[i];
    return strOut;
}




///////////////////////////////////////////////////////////////////////////////////////// Parser

var Reader = function(string) {
    this.string = string;
    position = 0;
    this._next_ = function() {
        while (position < string.length && string.charAt(position) == " ") position++;
        if (position >= string.length) return "EOF";
        return string.charAt(position);
    }
    this.next = function() {
        return this._next_();
    }
    this.consume = function() {
        if (this._next_ != "EOF") position++;
    }
}

//O -> A {"|" A}
//A -> B {"&" B}
//B -> {"~" B} C
//C -> "(" A ")"
//D -> Variable | ("0" | "1")

function parse(r) { return firstP(new Reader(r)); }

function firstP(r){
    var curr = secondP(r);
    while (r.next() == "|") {
        r.consume();
        curr = new Or(curr, secondP(r));
    }
    return curr;
}

function secondP(r){
    var curr = thirdP(r);
    while (r.next() == "&") {
        r.consume();
        curr = new And(curr, thirdP(r));
    }
    return curr;
}

function thirdP(r){
    if (r.next() == "~"){
        r.consume();
        return new Not(thirdP(r));
    } else return fourthP(r);
}

function fourthP(r){
    if (r.next() == "("){
        r.consume();
        var curr = firstP(r);
        if (r.next() != ")") return "ERROR PARENTHESIS BROKEN";
        else return curr;
    } else return fifthP(r);
}

function fifthP(r){
    var s = r.next();
    if (s == '1' || s == '0') {
        r.consume();
        return new Const(s);
    }
    if (isAlphabetic(s))
    {
        r.consume();
        return new Variable(s)
    }
}

//print(new Or(new Const(1), new Variable("x")))
//print(parse("x & y | (1 & ~z)").toString())
//print(new Or(new And(new Variable("x"), new Variable("y")), new Const(1)).toString());
//print(parse("a & b & c").evaluate("abc", [true, true, true]))
print(dnf("a | b | c"))