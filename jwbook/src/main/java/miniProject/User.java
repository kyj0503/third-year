package miniProject;

public class User {
    private String id;
    private String password;
    private String name;
    private int game1hp;
    private int game2hp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGame1hp() {
        return game1hp;
    }

    public void setGame1hp(int game1hp) {
        this.game1hp = game1hp;
    }

    public int getGame2hp() {
        return game2hp;
    }

    public void setGame2hp(int game2hp) {
        this.game2hp = game2hp;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", password=" + password + ", name=" + name + ", game1hp=" + game1hp + ", game2hp=" + game2hp + "]";
    }
}
