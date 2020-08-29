/*
信息:
*/
package test;

public class TPrilivage {
    private String prilivage;
    private String name;

    public String getPrilivage() {
        return prilivage;
    }

    public void setPrilivage(String prilivage) {
        this.prilivage = prilivage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TPrilivage that = (TPrilivage) o;

        if (prilivage != null ? !prilivage.equals(that.prilivage) : that.prilivage != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = prilivage != null ? prilivage.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
