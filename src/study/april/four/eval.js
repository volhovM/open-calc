/**
 * @author volhovm
 * Created on 27.04.14
 */

function cnst(a){
    return function() { return a; };
}

function variable(a){
    switch (a){
        case "x":
            return function(x, y, z) {return x;};
            break;
        case "y":
            return function(x, y, z) {return y;};
            break;
        case "z":
            return function(x, y, z) {return z;};
            break;
    }
}

function binaryOperation(foo){
    return function(a, b){
        return function(x, y, z){
            return foo(a(x, y, z), b(x, y, z));
        };
    }
}

function unaryOperation(foo){
    return function(a){
        return function(x, y, z){
            return foo(a(x, y, z));
        }
    }
}

var add = binaryOperation(function(a, b){ return a + b;});
var subtract = binaryOperation(function(a, b){ return a - b;});
var multiply = binaryOperation(function(a, b){ return a * b;});
var divide = binaryOperation(function(a, b) { return a / b;});
var abs = unaryOperation(function(a){ return Math.abs(a);})
var log = unaryOperation(function(a){ return Math.log(a);})

//var expr = add(
//    subtract(
//        multiply(
//            variable("x"),
//            variable("x")
//        ),
//        multiply(
//            cnst(2),
//            variable("x")
//        )
//    ),
//    cnst(1)
//);

//for(var i = 0; i < 10; i ++){
//    print(expr(i));
//}
function readInt(string, i){

    print("readed " + number)
    return number;
}

function parse(string){
    var stack = new Array();
    for(var i = 0; i < string.length; i++){
        switch (string.charAt(i)){
            case " ": break;
            case "x":
                stack.push(variable("x"));
                break;
            case "y":
                stack.push(variable("y"));
                break;
            case "z":
                stack.push(variable("z"));
                break;
            case "l":
                stack.push(log(stack.pop()));
                i += 2;
                break;
            case "a":
                stack.push(abs(stack.pop()));
                i += 2;
                break;
            case "+":
                stack.push(add(stack.pop(), stack.pop()));
                break;
            case "-":
                if (i + 1 < string.length && string.charAt(i + 1) != " "){
                    var number = string.charAt(i);
                    while ((i+1 < string.length) && string.charAt(i+1).match("[0-9]+") != null) {
                        i++;
                        number += string.charAt(i);
                    }
//                    print(number);
                    stack.push(cnst(parseInt(number)));
                } else {
                    var v = stack.pop();
                    stack.push(subtract(stack.pop(), v));
                }
                break;
            case "*":
                stack.push(multiply(stack.pop(), stack.pop()));
                break;
            case "/":
                var v = stack.pop();
                stack.push(divide(stack.pop(), v));
                break;
            default:
                var number = string.charAt(i);
                if (number.match("[0-9]") == null) { return ""; }
                while ((i+1 < string.length) && (string.charAt(i+1).match("[0-9]+") != null)) {
                    i++;
                    number += string.charAt(i);
                }
                stack.push(cnst(parseInt(number)));
                break;
        }
    }
    return stack.pop();
}

//print(parse("0 20 -")(0, 0, 0));

function parseAndEval(a, b){
    var calcParserClass = Java.type("calc.parser.ExpressionParser");
    print(calcParserClass.parseAndEval("-i", a, b));
}

//parseAndEval("2+3");