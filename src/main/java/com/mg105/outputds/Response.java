package com.mg105.outputds;

public class Response {

    /**
     * The Response class is a struct like class that represents the data returned when making a call and if that call
     * was successful
     *
     * A call is something that either retrieves data from the inner level system or modifies it or both
     */

    private boolean isSuccess;

    private String msg;

    // Maybe change this to not be an array of object
    // All items in Array must be immutable!
    private Object[] data = {};

    public Response(boolean isSuccess, String msg, Object[] data){
        this.isSuccess = isSuccess;
        this.msg = msg;
        this.data = data;
    }

    public Response(boolean isSuccess, String msg){
        this.isSuccess = isSuccess;
        this.msg = msg;
    }

    /**
     * Returns if the call was successful or not
     * @return if the call was successful
     */
    public boolean isSuccess() {
        return this.isSuccess;
    }

    /**
     * Returns the message of the response
     *
     * The message of the response is a brief description that represents the action performed by the call or
     * why the call was not successful. Messages are useful to display in AlertBoxes.
     *
     * @return the message of the response
     */
    public String getMessage(){
        return this.msg;
    }

    /**
     * Return the data of the response
     *
     * The data of the response represents information that the client wants to update after the
     * was made. Can return an empty list.
     *
     * All items in the returned array will be immutable
     *
     * @return
     */
    public Object[] getData(){
        return this.data;
    }
}
