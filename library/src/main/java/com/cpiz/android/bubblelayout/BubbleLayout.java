package com.cpiz.android.bubblelayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * 气泡样式的RelativeLayout布局
 * 支持在XML布局中通过自定义属性设定样式，
 * Created by caijw on 2016/5/26.
 */
@SuppressWarnings("unused")
public class BubbleLayout extends RelativeLayout {
    private BubbleDrawable mBubbleDrawable = new BubbleDrawable();
    private BubbleDrawable.ArrowDirection mArrowDirection = BubbleDrawable.ArrowDirection.None;
    private int mArrowToViewId = 0;
    private float mArrowHeight = 0;
    private float mArrowWidth = 0;
    private int mPaddingLeftOffset = 0, mPaddingTopOffset = 0, mPaddingRightOffset = 0, mPaddingBottomOffset = 0;
    private float mCornerTopLeftRadius = 0;
    private float mCornerTopRightRadius = 0;
    private float mCornerBottomLeftRadius = 0;
    private float mCornerBottomRightRadius = 0;
    private int mBackColor = 0xCC000000;
    private int mBorderColor = Color.WHITE;
    private float mBorderWidth = 0;

    public BubbleLayout(Context context) {
        super(context);
        init(context, null);
    }

    public BubbleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public BubbleLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public BubbleLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.BubbleLayout);
            mArrowDirection = BubbleDrawable.ArrowDirection.valueOf(
                    ta.getInt(R.styleable.BubbleLayout_bb_arrowDirection, 0));
            mArrowHeight = ta.getDimension(R.styleable.BubbleLayout_bb_arrowHeight, PixelUtil.dpToPx(6));
            mArrowWidth = ta.getDimension(R.styleable.BubbleLayout_bb_arrowWidth, PixelUtil.dpToPx(10));
            mArrowToViewId = ta.getResourceId(R.styleable.BubbleLayout_bb_arrowTo, 0);

            float radius = ta.getDimension(R.styleable.BubbleLayout_bb_cornerRadius, PixelUtil.dpToPx(10));
            mCornerTopLeftRadius = mCornerTopRightRadius = mCornerBottomLeftRadius = mCornerBottomRightRadius = radius;
            mCornerTopLeftRadius = ta.getDimension(R.styleable.BubbleLayout_bb_cornerTopLeftRadius, mCornerTopLeftRadius);
            mCornerTopRightRadius = ta.getDimension(R.styleable.BubbleLayout_bb_cornerTopRightRadius, mCornerTopLeftRadius);
            mCornerBottomLeftRadius = ta.getDimension(R.styleable.BubbleLayout_bb_cornerBottomLeftRadius, mCornerTopLeftRadius);
            mCornerBottomRightRadius = ta.getDimension(R.styleable.BubbleLayout_bb_cornerBottomRightRadius, mCornerTopLeftRadius);

            mBackColor = ta.getColor(R.styleable.BubbleLayout_bb_backColor, 0xCC000000);
            mBorderColor = ta.getColor(R.styleable.BubbleLayout_bb_borderColor, Color.WHITE);
            mBorderWidth = ta.getDimension(R.styleable.BubbleLayout_bb_borderWidth, 0);

            ta.recycle();
        }

        updateBackground(getWidth(), getHeight());
    }

    public BubbleDrawable.ArrowDirection getArrowDirection() {
        return mArrowDirection;
    }

    /**
     * 设置箭头朝向
     *
     * @param arrowDirection 上下左右或者无
     */
    public void setArrowDirection(BubbleDrawable.ArrowDirection arrowDirection) {
        mArrowDirection = arrowDirection;
        updateBackground(getWidth(), getHeight());
    }

    /**
     * 设置箭头指向的View对象ID
     *
     * @param arrowToViewId 指向的ViewId
     */
    public void setArrowToViewId(int arrowToViewId) {
        mArrowToViewId = arrowToViewId;
        updateBackground(getWidth(), getHeight());
    }

    public int getArrowToViewId() {
        return mArrowToViewId;
    }

    /**
     * 设置箭头三角形厚度
     *
     * @param arrowHeight 箭头厚度
     */
    public void setArrowHeight(float arrowHeight) {
        mArrowHeight = arrowHeight;
        updateBackground(getWidth(), getHeight());
    }

    public float getArrowHeight() {
        return mArrowHeight;
    }

    /**
     * 设置箭头三角形底宽
     *
     * @param arrowWidth 箭头底边宽度
     */
    public void setArrowWidth(float arrowWidth) {
        mArrowWidth = arrowWidth;
        updateBackground(getWidth(), getHeight());
    }

    public float getArrowWidth() {
        return mArrowWidth;
    }

    /**
     * 设置气泡背景色
     *
     * @param backColor 气泡背景颜色
     */
    public void setBackColor(int backColor) {
        mBackColor = backColor;
        updateBackground(getWidth(), getHeight());
    }

    public int getBackColor() {
        return mBackColor;
    }

    /**
     * 设置边框线颜色
     *
     * @param borderColor 边框颜色
     */
    public void setBorderColor(int borderColor) {
        mBorderColor = borderColor;
        updateBackground(getWidth(), getHeight());
    }

    public int getBorderColor() {
        return mBorderColor;
    }

    /**
     * 设置边框线宽
     *
     * @param borderWidth 边框厚度
     */
    public void setBorderWidth(float borderWidth) {
        mBorderWidth = borderWidth;
        updateBackground(getWidth(), getHeight());
    }

    public float getBorderWidth() {
        return mBorderWidth;
    }

    /**
     * 设置边角弧度
     * 可以为四角指定不同弧度
     *
     * @param topLeft     左上角
     * @param topRight    右上角
     * @param bottomRight 右下角
     * @param bottomLeft  左下角
     */
    public void setCornerRadius(float topLeft, float topRight, float bottomRight, float bottomLeft) {
        mCornerTopLeftRadius = topLeft;
        mCornerTopRightRadius = topRight;
        mCornerBottomRightRadius = bottomRight;
        mCornerBottomLeftRadius = bottomLeft;
        updateBackground(getWidth(), getHeight());
    }

    public float getCornerTopLeftRadius() {
        return mCornerTopLeftRadius;
    }

    public float getCornerTopRightRadius() {
        return mCornerTopRightRadius;
    }

    public float getCornerBottomLeftRadius() {
        return mCornerBottomLeftRadius;
    }

    public float getCornerBottomRightRadius() {
        return mCornerBottomRightRadius;
    }

    /**
     * 设定Padding
     * 将自动将箭头区域占用空间加入Padding，使内容能够完全被气泡包含
     *
     * @param left   用户指定的 LeftPadding
     * @param top    用户指定的 TopPadding
     * @param right  用户指定的 RightPadding
     * @param bottom 用户指定的 BottomPadding
     */
    @Override
    public void setPadding(int left, int top, int right, int bottom) {
        resetPadding(left, top, right, bottom);
    }

    @Override
    public int getPaddingLeft() {
        return super.getPaddingLeft() - mPaddingLeftOffset;
    }

    @Override
    public int getPaddingTop() {
        return super.getPaddingTop() - mPaddingTopOffset;
    }

    @Override
    public int getPaddingRight() {
        return super.getPaddingRight() - mPaddingRightOffset;
    }

    @Override
    public int getPaddingBottom() {
        return super.getPaddingBottom() - mPaddingBottomOffset;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed) {
            updateBackground(right - left, bottom - top);
        }
    }

    @SuppressWarnings("SuspiciousNameCombination")
    private void resetPadding(int left, int top, int right, int bottom) {
        mPaddingLeftOffset = mPaddingTopOffset = mPaddingRightOffset = mPaddingBottomOffset = 0;
        switch (mArrowDirection) {
            case Left:
                mPaddingLeftOffset += mArrowHeight;
                break;
            case Up:
                mPaddingTopOffset += mArrowHeight;
                break;
            case Right:
                mPaddingRightOffset += mArrowHeight;
                break;
            case Down:
                mPaddingBottomOffset += mArrowHeight;
                break;
        }
        super.setPadding(left + mPaddingLeftOffset, top + mPaddingTopOffset,
                right + mPaddingRightOffset, bottom + mPaddingBottomOffset);
    }

    // 方便计算用的中间值对象，避免重复创建
    private int[] mLocation = new int[2];
    private Rect mRectTo = new Rect();
    private Rect mRectSelf = new Rect();

    private void updateBackground(int width, int height) {
        int arrowToOffsetX = 0;
        int arrowToOffsetY = 0;
        if (mArrowToViewId != 0 && getParent() instanceof ViewGroup) {
            View arrowToView = ((ViewGroup) getParent()).findViewById(mArrowToViewId);
            arrowToView.getLocationInWindow(mLocation);
            mRectTo.set(mLocation[0], mLocation[1], mLocation[0] + arrowToView.getWidth(), mLocation[1] + arrowToView.getHeight());

            getLocationInWindow(mLocation);
            mRectSelf.set(mLocation[0], mLocation[1], mLocation[0] + getWidth(), mLocation[1] + getHeight());

            arrowToOffsetX = mRectTo.centerX() - mRectSelf.centerX();
            arrowToOffsetY = mRectTo.centerY() - mRectSelf.centerY();
            mArrowDirection = getArrowDirection(width, height, arrowToOffsetX, arrowToOffsetY);
        }
        resetPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());

        mBubbleDrawable.resetRect(width, height);
        mBubbleDrawable.setCornerRadius(mCornerTopLeftRadius, mCornerTopRightRadius, mCornerBottomRightRadius, mCornerBottomLeftRadius);
        mBubbleDrawable.setBackColor(mBackColor);
        mBubbleDrawable.setBorderWidth(mBorderWidth);
        mBubbleDrawable.setBorderColor(mBorderColor);
        mBubbleDrawable.setArrowDirection(mArrowDirection);
        mBubbleDrawable.setArrowTo(arrowToOffsetX, arrowToOffsetY);
        mBubbleDrawable.setArrowHeight(mArrowHeight);
        mBubbleDrawable.setArrowWidth(mArrowWidth);
        setBackground(mBubbleDrawable);
    }

    /**
     * 根据目标对象相对中心位置，推导箭头朝向
     * 只有目标点落在气泡容器横纵延长区间内，才能获得确定的方向，否则不显示箭头
     *
     * @param width   自己的宽度
     * @param height  自己的高度
     * @param offsetX 目标对象中心相对X
     * @param offsetY 目标对象中心相对Y
     * @return
     */
    private BubbleDrawable.ArrowDirection getArrowDirection(int width, int height, int offsetX, int offsetY) {
        int targetCenterX = offsetX + width / 2;
        int targetCenterY = offsetY + height / 2;

        if (targetCenterX < 0 && targetCenterY > 0 && targetCenterY < height) {
            return BubbleDrawable.ArrowDirection.Left;
        }

        if (targetCenterY < 0 && targetCenterX > 0 && targetCenterX < width) {
            return BubbleDrawable.ArrowDirection.Up;
        }

        if (targetCenterX > width && targetCenterY > 0 && targetCenterY < height) {
            return BubbleDrawable.ArrowDirection.Right;
        }

        if (targetCenterY > height && targetCenterX > 0 && targetCenterX < width) {
            return BubbleDrawable.ArrowDirection.Down;
        }

        return BubbleDrawable.ArrowDirection.None;
    }
}
