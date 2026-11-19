
class InsufficientFundsException extends Exception {
    InsufficientFundsException(String msg) {
        super(msg);
    }
}

class InsufficientChangeException extends Exception {
    InsufficientChangeException(String msg) {
        super(msg);
    }
}

class SoldOutException extends Exception {
    SoldOutException(String msg) {
        super(msg);
    }
}

class InsufficientQuantityException extends Exception {
    InsufficientQuantityException(String msg) {
        super(msg);
    }
}
