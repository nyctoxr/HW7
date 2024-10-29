package Service;

import Model.Tag;

public class TagService {
    private Tag[] tags;

    public TagService() {
        this.tags = new Tag[]{
                new Tag(1, "Java"),
                new Tag(2, "OOP"),
                new Tag(3, "cos"),
                new Tag(4,"sin"),
        };
    }

    public Tag[] getAllTags() {
        return tags;
    }

    public Tag getTagById(int id) {
        for (Tag tag : tags) {
            if (tag.getTagId() == id) {
                return tag;
            }
        }
        return null;
    }
}