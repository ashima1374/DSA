/**
 * @return {Generator<number>}
 */
var fibGenerator = function*() {

    // First two Fibonacci numbers
    let a = 0;
    let b = 1;

    // Keep generating numbers forever
    while (true) {

        // Give the current Fibonacci number
        yield a;

        // Move to the next Fibonacci number
        let next = a + b;
        a = b;
        b = next;
    }
};