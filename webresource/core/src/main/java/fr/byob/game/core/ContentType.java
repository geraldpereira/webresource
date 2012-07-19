package fr.byob.game.core;

/**
 * The type of content type
 * 
 * @author Gérald Pereira
 * 
 */
public enum ContentType {
	APPLICATION_JSON("application/json"), TEXT_PLAIN("text/plain");

	private final String type;

	private ContentType(final String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
