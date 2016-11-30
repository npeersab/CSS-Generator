package css;

public enum PropertyType {
	animation("Animation"), background_and_borders("Background and Borders"),
	basic_box("Basic Box"), basic_user_interface("Basic User Interface"),
	color("Color"), flexible_box("Flexible Box"), fonts("Fonts"),
	multi_column("Multi-Column"), speech("Speech"), table("Table"),
	text("Text"), text_decoration("Text Decoration"), writing_modes("Writing Modes");
	
	private String name;
	
	private PropertyType(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
}
