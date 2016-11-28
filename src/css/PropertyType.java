package css;

public enum PropertyType {
	animation("Animation"), background_and_borders("Background and Borders"),
	basic_box("Basic Box"), basic_user_interface("Basic User Interface"),
	color("Color"), filter_effects("Filter Effects"), flexible_box("Flexible Box"),
	fonts("Fonts"), generated_content("Generated Content"), 
	image_replaced_content("Image/Replaced Content"), marquee("Marquee"), masking("Masking"),
	multi_column("Multi-Column"), paged_media("Paged Media"), speech("Speech"), table("Table"),
	text("Text"), text_decoration("Text Decoration"), writing_modes("Writing Modes");
	
	private String name;
	
	private PropertyType(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
}
