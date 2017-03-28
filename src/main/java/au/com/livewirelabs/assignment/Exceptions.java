package au.com.livewirelabs.assignment;

/**
 * Exceptions
 * A couple of custom exceptions. Couldn't find any actual ones named as such, so made my own.
 */
class InvalidCodeException extends Exception {
    InvalidCodeException(String msg){
        System.out.println("Code Exception: " + msg + " is not a valid code.");
    }
}

class InsufficientUnitsException extends Exception {
    InsufficientUnitsException(Integer msgA, String msgB, Integer msgC){
        System.out.println("Insufficient Units Exception: there's " + msgA + " units in " + msgB + ", cannot buy " + msgC + " units.");
    }
}
