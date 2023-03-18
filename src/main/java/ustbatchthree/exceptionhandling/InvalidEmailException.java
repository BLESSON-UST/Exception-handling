package ustbatchthree.exceptionhandling;



class InvalidEmailException extends Exception {
    public InvalidEmailException(String message) {
        super(message);
    }
}