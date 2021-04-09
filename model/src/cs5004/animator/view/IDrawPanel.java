package cs5004.animator.view;

import java.util.List;

import cs5004.animator.model.IShape;

public interface IDrawPanel {
    void draw(List<IShape> shapeList);
}
