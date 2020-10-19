package com.chursinov.beautysalon.controller.action;

public class ActionResult {

	private final String OUTPUT;
	private final ResponseType RESPONSE_TYPE;

	public ActionResult(String output) {
		this.OUTPUT = output;
		this.RESPONSE_TYPE = ResponseType.FORWARD;
	}

	public ActionResult(String output, ResponseType responseType) {
		this.OUTPUT = output;
		this.RESPONSE_TYPE = responseType;
	}

	public String getOutput() {
		return OUTPUT;
	}

	public ResponseType getResponseType() {
		return RESPONSE_TYPE;
	}
}
