

   /**
 * @author volhovm
 * Created on 27.04.14
 */

function cnst(a){
    return function() { return a; };
}

function variable(a){
    if (a == "x") return function(c) {return c;};
    else return function(c) {return 0};
}

function binaryOperation(foo){
    return function(a, b){
        return function(x){
            return foo(a(x), b(x));
        };
    }
}

var add = binaryOperation(function(a, b){ return a + b;});
var subtract = binaryOperation(function(a, b){ return a - b;});
var multiply = binaryOperation(function(a, b){ return a * b;});
var divide = binaryOperation(function(a, b) {return a / b;});

var expr = add(
    subtract(
        multiply(
            variable("x"),
            variable("x")
        ),
        multiply(
            cnst(2),
            variable("x")
        )
    ),
    cnst(1)
);

for(var i = 0; i < 10; i ++){
    print(expr(i));
}

function polishParse(string){
    var stack = new Array();
    for(var i = 0; i < string.length; i++){
        switch (string.charAt(i)){
            case " ": break;
            case "x":
                stack.push(variable("x"));
                break;
            case "+":
                stack.push(add(stack.pop(), stack.pop()));
                break;
            case "-":
                stack.push(subtract(stack.pop(), stack.pop()));
                break;
            case "*":
                stack.push(multiply(stack.pop(), stack.pop()));
                break;
            case "/":
                stack.push(divide(stack.pop(), stack.pop()));
                break;
            default:
                var number = string.charAt(i);
                if (!number.match("[0-9]+")) { break; }
                while (string.charAt(i+1).match("[0-9]+")) {
                    i++;
                    number.concat(string.charAt(i));
                }
                stack.push(cnst(parseInt(number)));
                break;
        }
    }
    return stack.pop();
}

print(polishParse("2 2+")(0));

function parseAndEval(a, b){
    var calcParserClass = Java.type("calc.parser.ExpressionParser");
    print(calcParserClass.parseAndEval("-i", a, b));
}

parseAndEval("2+3");