package game.sudoku.domains;

public class ApiError {

	String code;
	String title;
	String exception;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public ApiError(String code, String title, String exception) {
		this.code = code;
		this.title = title;
		this.exception = exception;
	}

	public ApiError() {
	}
}
