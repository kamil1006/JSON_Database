import java.util.stream.IntStream;

interface Movable {
    int getX();

    int getY();

    void setX(int newX);

    void setY(int newY);
}

interface Storable {
    int getInventoryLength();

    String getInventoryItem(int index);

    void setInventoryItem(int index, String item);
}

interface Command {
    void execute();

    void undo();
}

class CommandMove implements Command {
    Movable entity;
    int xMovement;
    int yMovement;

    @Override
    public void execute() {
        entity.setX(entity.getX() + xMovement);
        entity.setY(entity.getY() + yMovement);
    }

    @Override
    public void undo() {
        entity.setX(entity.getX() - xMovement);
        entity.setY(entity.getY() - yMovement);
    }
}

class CommandPutItem implements Command {
    Storable entity;
    String item;

    @Override
    public void execute() {
        IntStream.range(0, entity.getInventoryLength())
                .filter(i -> entity.getInventoryItem(i) == null)
                .findFirst()
                .ifPresent(i -> entity.setInventoryItem(i, item));
    }

    @Override
    public void undo() {
        IntStream.range(0, entity.getInventoryLength())
                .map(i -> entity.getInventoryLength() - i - 1)
                .filter(i -> entity.getInventoryItem(i) != null)
                .findFirst()
                .ifPresent(i -> entity.setInventoryItem(i, null));
    }
}
/*


class CommandPutItem implements Command {
    Storable entity;
    String item;
    int position = -1;

    @Override
    public void execute() {
        int len = entity.getInventoryLength();
        for (int i = 0; i < len; i++) {
            if (entity.getInventoryItem(i) == null) {
                entity.setInventoryItem(i, item);
                position = i;
                break;
            }
        }
    }

    @Override
    public void undo() {
        if (position >= 0) {
            entity.setInventoryItem(position, null);
        }
    }
}



 */