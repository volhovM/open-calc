/**
 * @author volhovm
 * Created on 5/15/14
 */
var println = function(s){
    print ((s == null ? "null" : s) + "\r\n");
}

var point = {
    x: 10,
    y: 20
}
//
//var colorPoint = Object.create(point);
//colorPoint.color = "red";
//
//Object.prototype.hello = "world";
//println("fdsfa".hello);
//
//var trash = Object.create(null);
//println(trash.hello);

var shiftedPoint = Object.create(point);
shiftedPoint.dx = 2;
shiftedPoint.dy = 3;
shiftedPoint.getX = function() { return this.x + this.dx; }
shiftedPoint.getY = function() { return this.y + this.dy; }

function dump(obj){
    for (var name in obj){
        if (typeof(obj[name]) == "function") {
            print("    " + name + "()  =  " + obj[name]());
        } else {
            print("    " + name + "  =  " + obj[name]);
        }
    }
}

var spp = {x: -1, y: -2, dx: -3, dy: -4};
println(shiftedPoint.getX.apply(spp));

function Point(x, y){
    this.x = x;
    this.y = y;
}

//Point.getX = function() { return this.x; }
//Point.getY = function() { return this.y; }
Point.prototype.getX = function() { return this.x; }
Point.prototype.getY = function() { return this.y; }

var point = new Point(10, 20);
dump(point);