function dump() {
    for (var i = 0; i < arguments.length; i++){
        println(arguments[i]);
    }
}

dump(1.2, "hello", 1);

function printf() {
    var format = arguments[0];
    var j = 1;
    for (var i = 0; i < format.length; i++) {
        if (format[i] == "%") {
            switch (format[i + 1]) {
                case "d":
                    print(Number(arguments[j++]))
                    break;
                case "s":
                    print("" + arguments[j++])
                    break;
            }
            i += 1;
        } else {
            print(format[i]);
        }
    }
}

printf("%d+%d=%s", 1, 2, "6??!!");

function add(a, b) {
    println(typeof(a))
    return a + b;
}

var add2 = add;

function sort(a) {
    for (var i = 0; i < a.length; i++) {
        var minJ = i;
        for (var j = i; j < a.length; j++){
            if (a[minJ] > a[j]) {
                minJ = j;
            }
        }
        var t = a[i];
        a[i] = a[minJ];
        a[minJ] = t;
    }
    return a;
}

function csort(comparator, a) {
    for (var i = 0; i < a.length; i++) {
        var minJ = i;
        for (var j = i; j < a.length; j++){
            if (comparator(a[minJ], a[j]) > 0) {
                minJ = j;
            }
        }
        var t = a[i];
        a[i] = a[minJ];
        a[minJ] = t;
    }
    return a;
}

a = [40, 20, 10, 30];
println(sort(a));
println(a); //меняется число

var absCompare = function(a, b) {
    return Math.abs(a) - Math.abs(b);
}