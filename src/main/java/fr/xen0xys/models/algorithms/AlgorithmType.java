package fr.xen0xys.models.algorithms;

public enum AlgorithmType {
    BUBBLE_SORT("Bubble Sort"),
    SELECTION_SORT("Selection Sort"),
    INSERTION_SORT("Insertion Sort");

    private final String displayName;

    AlgorithmType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
