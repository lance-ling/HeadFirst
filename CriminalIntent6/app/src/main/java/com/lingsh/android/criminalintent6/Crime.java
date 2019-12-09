package com.lingsh.android.criminalintent6;

import java.util.Date;
import java.util.UUID;

/**
 * 陋习记录
 * 1. 标题 mTitle
 * 2. 日期 mDate
 * 3. 是否解决陋习 mSolved
 */
public class Crime {

    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;
    // 添加是否需要报警属性 0:不需要 1:需要
    private int mRequiresPolice;

    public Crime() {
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    public int getRequiresPolice() {
        return mRequiresPolice;
    }

    public void setRequiresPolice(int requiresPolice) {
        mRequiresPolice = requiresPolice;
    }
}
