public class Feature {
    String text;
    Double number;
    Boolean value;

    public Feature(String text) {
        this.text = text;
        this.number = null;
        this.value = null;
    }

    public Feature(double number) {
        this.text = null;
        this.number = number;
        this.value = null;
    }

    public Feature(boolean value) {
        this.text = null;
        this.number = null;
        this.value = value;
    }

    public double getNumber() {
        return number;
    }

    public String getText() {
        return text;
    }

    public Boolean getValue() {
        return value;
    }

    public void setNumber(double number) {
        this.number = number;
    }

    public void setText(String text) {
        this.text = text;
    }
    public void setValue(Boolean value) {
        this.value = value;
    }

    public boolean isNumber() {
        return this.number != null;
    }
    public boolean isText() {
        return this.text != null;
    }
    public boolean isBoolean() {
        return this.value != null;
    }
}
