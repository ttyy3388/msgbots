package org.beuwi.msgbots.view.gui.editor;

// Cursor Position
public class Position {
    private final int lineNumber;
    private final int column;

    public Position(int lineNumber, int column) {
        this.lineNumber = lineNumber;
        this.column = column;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public int getColumn() {
        return column;
    }
}
