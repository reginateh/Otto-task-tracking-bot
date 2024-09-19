package otto;

import java.util.ArrayList;

public class TagList {
    private ArrayList<Tag> tags;

    public TagList() {
        this.tags = new ArrayList<>();
    }

    public TagList(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    /**
     * Returns true if the tag list contains the specified tag.
     *
     * @param tagName Name of the tag. Doesn't contain prefix #.
     */
    public boolean findTag(String tagName) {
        for (Tag tag : tags) {
            if (tag.getTagName().equals(tagName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (Tag tag : tags) {
            res.append(String.format("#%s ", tag.toString()));
        }
        return res.toString();
    }
}
