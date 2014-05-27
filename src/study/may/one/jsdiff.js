/**
 * @author volhovm
 * Created on 5/19/14
 */

function Expression(evaluate, toString, diff){
    this.evaluate = evaluate;
    this.toString = toString;
    this.diff = diff;
}

function BinaryOperation(op1, op2, evaluateFoo, concatSymbol, diffFoo){
    this.op1 = op1;
    this.op2 = op2;
    this.base = Expression;
    this.base(
        function(){ return evaluateFoo(op1.evaluate.apply(this, arguments), op2.evaluate.apply(this, arguments)); },
        function(){ return op1.toString() + " " + op2.toString() + " " + concatSymbol + " " ; },
        diffFoo
        );
}
BinaryOperation.prototype = new Expression;

function UnaryOperation(op1, evaluateFoo, toString, diffFoo) {
    this.op1 = op1;
    this.base = Expression;
    this.base(
        function(){ return evaluateFoo(op1.evaluate.apply(this, arguments));},
        toString,
        diffFoo
        );
}
UnaryOperation.prototype = new Expression;


function Const(a){
    this.base = Expression;
    this.base(
        function(){ return a; },
        function(){ return "" + a },
        function(){ return ZERO; });
};
Const.prototype = new Expression;

var ZERO = new Const(0);
var ONE = new Const(1);

var varList = "xyz";

function Variable(type){
    this.base = Expression;
    this.base(
        function(){ return arguments[varList.indexOf(type)]; },
        function(){ return type; },
        function(s){ return (s == type) ? ONE : ZERO; });
};
Variable.prototype = new Expression;

function Add(a, b){
    this.base = BinaryOperation;
    this.base(
        a, b,
        function(x, y){ return x + y; },
        "+",
        function(s){ return new Add(a.diff(s), b.diff(s)); }
        );
};
Add.prototype = new BinaryOperation;

function Subtract(a, b){
    this.base = BinaryOperation;
    this.base(
        a, b,
        function(x, y){ return x - y; },
        "-",
        function(s){ return new Subtract(a.diff(s), b.diff(s)); }
        );
}
Subtract.prototype = new BinaryOperation;


function Multiply(a, b){
    this.base = BinaryOperation;
    this.base(
        a, b,
        function(a, b){ return a * b; },
        "*",
        function(s){ return new Add(new Multiply(a.diff(s), b), new Multiply(b.diff(s), a)); }
    );
}
Multiply.prototype = new BinaryOperation;

function Divide(a, b){
    this.base = BinaryOperation;
    this.base(
        a, b,
        function(x, y){ return x / y; },
        "/",
        function(s){ return new Divide(
            new Subtract(new Multiply(a.diff(s), b), new Multiply(b.diff(s), a)), new Multiply(b, b)) }
    )
}
Divide.prototype = new BinaryOperation;

function Sin(a){
    this.base = UnaryOperation;
    this.base(
        a,
        Math.sin,
        function(){ return a.toString() + " sin "; },
        function(s){ return new Multiply(a.diff(s), new Cos(a)); }
    )
}
Sin.prototype = new UnaryOperation;

function Cos(a){
    this.base = UnaryOperation;
    this.base(
        a,
        Math.cos,
        function(){ return a.toString() + " cos "; },
        function(s){ return new Subtract(ZERO, new Multiply(a.diff(s), new Sin(a))); }
    );
}
Cos.prototype = new UnaryOperation;

function parse(string){
    var stack = new Array();
    for(var i = 0; i < string.length; i++){
        switch (string.charAt(i)){
            case " ": break;
            case "x":
                stack.push(new Variable("x"));
                break;
            case "y":
                stack.push(new Variable("y"));
                break;
            case "z":
                stack.push(new Variable("z"));
                break;
            case "+":
                stack.push(new Add(stack.pop(), stack.pop()));
                break;
            case "-":
                var a = stack.pop();
                stack.push(new Subtract(stack.pop(), a));
                break;
            case "*":
                stack.push(new Multiply(stack.pop(), stack.pop()));
                break;
            case "/":
                var a = stack.pop();
                stack.push(new Divide(stack.pop(), a));
                break;
            case "s":
                stack.push(new Sin(stack.pop()));
                i+= 2;
                break;
            case "c":
                stack.push(new Cos(stack.pop()));
                i+= 2;
                break;
            default:
                var number = string.charAt(i);
                if (!number.match("[0-9.]+")) { break; }
                while (string.charAt(i+1).match("[0-9.]+")) {
                    i++;
                    number += string.charAt(i);
                }
                stack.push(new Const(parseFloat(number)));
                break;
        }
    }
    return stack.pop();
}
