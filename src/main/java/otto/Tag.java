package otto;

public class Tag {
    private String tagName;

    public Tag(String tagName) {
        this.tagName = tagName;
    }

    public String getTagName() {
        return this.tagName;
    }

    @Override
    public String toString() {
        return this.tagName;
    }
}
