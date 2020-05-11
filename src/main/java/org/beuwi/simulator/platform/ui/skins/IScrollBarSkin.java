package org.beuwi.simulator.platform.ui.skins;

public class IScrollBarSkin // implements Skin<ScrollBar>
{
	/* private ScrollBar scroll;
	private Rectangle track = new Rectangle();
	private Rectangle thumb = new Rectangle();
    private Region group;

	public IScrollBarSkin(final ScrollBar scroll)
	{
		this.scroll = scroll;

		this.group = new Region()
		{
			double dragPos;
			Point2D dragStart;

			final NumberBinding range = Bindings.subtract(scroll.maxProperty(), scroll.minProperty());
			final NumberBinding position = Bindings.divide(Bindings.subtract(scroll.valueProperty(), scroll.minProperty()), range);

			{
				getChildren().addAll(track, thumb);

				track.setManaged(false);
				track.getStyleClass().add("track");

				thumb.setManaged(false);
				thumb.getStyleClass().add("thumb");

				scroll.orientationProperty().addListener(observable -> setup());

				thumb.setOnMouseClicked(event ->
				{
					if (event.isSynthesized())
					{
						event.consume();
					}
					else
					{
						if (getSkinnable().getMax() > getSkinnable().getMin())
						{
							dragStart = localToParent(event.getX(), event.getY());
							double value = Utils.clamp(getSkinnable().getMin(), getSkinnable().getValue(), getSkinnable().getMax());
							dragPos = (value - getSkinnable().getMin()) / (getSkinnable().getMax() - getSkinnable().getMin());

							event.consume();
						}
					}
				});

				thumb.setOnMouseDragged(event ->
				{
					if (event.isSynthesized())
					{
						event.consume();
					}
					else
					{
						if (getSkinnable().getMax() > getSkinnable().getMin())
						{
							if (this.trackLength() > this.thumbLength())
							{
								Point2D pos = thumb.localToParent(event.getX(), event.getY());

								if (dragStart == null)
								{
									dragStart = thumb.localToParent(event.getX(), event.getY());
								}

								double value = getSkinnable().getOrientation() == Orientation.VERTICAL ? pos.getY() - dragStart.getY() : pos.getX() - this.dragStart.getX();
								double change = dragPos + value / (this.trackLength() - this.thumbLength());
								double newValue = change * (getSkinnable().getMax() - getSkinnable().getMin()) + getSkinnable().getMin();

								if (!Double.isNaN(newValue))
								{
									getSkinnable().setValue(boundedSize(getSkinnable().getMin(), newValue, getSkinnable().getMax()));
								}
							}

							event.consume();
						}
					}
				});

				setup();
			}

			private double trackLength()
			{
				return getSkinnable().getOrientation() == Orientation.VERTICAL ? track.getHeight() : track.getWidth();
			}

			private double thumbLength()
			{
				return getSkinnable().getOrientation() == Orientation.VERTICAL ? thumb.getHeight() : thumb.getWidth();
			}

			private double boundedSize(double min, double value, double max)
			{
				return Math.min(Math.max(value, min), Math.max(min, max));
			}

			private void setup()
			{
				track.widthProperty().unbind();
				track.heightProperty().unbind();

				if (scroll.getOrientation() == Orientation.HORIZONTAL)
				{
					track.relocate(0, -10);
					track.widthProperty().bind(scroll.widthProperty());
					track.setHeight(10);
				}
				else
				{
					track.relocate(-10, 0);
					track.setWidth(10);
					track.heightProperty().bind(scroll.heightProperty());
				}

				thumb.xProperty().unbind();
				thumb.yProperty().unbind();
				thumb.widthProperty().unbind();
				thumb.heightProperty().unbind();

				if (scroll.getOrientation() == Orientation.HORIZONTAL)
				{
					thumb.relocate(0, -10);
					thumb.widthProperty().bind(Bindings.max(10, scroll.visibleAmountProperty().divide(range).multiply(scroll.widthProperty())));
					thumb.setHeight(10);
					thumb.xProperty().bind(Bindings.subtract(scroll.widthProperty(), thumb.widthProperty()).multiply(position));
				}
				else
				{
					thumb.relocate(-10, 0);
					thumb.setWidth(10);
					thumb.heightProperty().bind(Bindings.max(10, scroll.visibleAmountProperty().divide(range).multiply(scroll.heightProperty())));
					thumb.yProperty().bind(Bindings.subtract(scroll.heightProperty(), thumb.heightProperty()).multiply(position));
				}
			}

			@Override
			protected double computeMaxWidth(double height)
			{
				if (scroll.getOrientation() == Orientation.HORIZONTAL)
				{
					return Double.MAX_VALUE;
				}

				return 10;
			}

			@Override
			protected double computeMaxHeight(double width)
			{
				if (scroll.getOrientation() == Orientation.VERTICAL)
				{
					return Double.MAX_VALUE;
				}

				return 10;
			}
		};
	}

	@Override
	public void dispose()
	{
		scroll = null;
		group = null;
	}

	@Override
	public Node getNode()
	{
		return group;
	}

	@Override
	public javafx.scene.control.ScrollBar getSkinnable()
	{
		return scroll;
	} */
}