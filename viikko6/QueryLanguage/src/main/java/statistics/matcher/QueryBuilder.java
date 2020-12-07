package statistics.matcher;

public class QueryBuilder {
    private Matcher matcher;

    public QueryBuilder() {
        matcher = new All();
    }

    public Matcher build() {
        Matcher m = matcher;
        matcher = new All();
        return m;
    }

    public QueryBuilder hasAtLeast(int value, String category) {
        this.matcher = new And( this.matcher, new HasAtLeast(value, category) );
        return this;
    }

    public QueryBuilder hasFewerThan(int value, String category) {
        this.matcher = new And( this.matcher, new HasFewerThan(value, category) );
        return this;
    }

    public QueryBuilder not(Matcher... matchers) {
        this.matcher = new Not(matchers);
        return this;
    }

    public QueryBuilder oneOf(Matcher m1, Matcher m2) {
        this.matcher = new Or(m1, m2);
        return this;
    }

    public QueryBuilder playsIn(String team) {
        this.matcher = new And(this.matcher, new PlaysIn(team));
        return this;
    }
}
