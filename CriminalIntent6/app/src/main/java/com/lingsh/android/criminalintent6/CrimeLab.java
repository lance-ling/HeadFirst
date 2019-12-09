package com.lingsh.android.criminalintent6;

import android.content.Context;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CrimeLab {
    private static CrimeLab sCrimeLab;

    // 使用一个更方便于查找的数据结构
    // private List<Crime> mCrimes;
    private Map<UUID, Crime> mCrimes;

    // 私有构造方法 单例做法
    private CrimeLab(Context context) {
        mCrimes = new LinkedHashMap<>();

        // 自测
        /*for (int i = 0; i < 100; i++) {
            Crime crime = new Crime();
            crime.setTitle("Crime # " + i);
            crime.setSolved((i % 3) == 0);
            // 每10条记录就有一条需要报警
            crime.setRequiresPolice((i % 10) == 0 ? 1 : 0);
            mCrimes.put(crime.getId(), crime);
        }*/
    }

    public void addCrime(Crime crime) {
        mCrimes.put(crime.getId(), crime);
    }

    public void delCrime(Crime crime) {
        mCrimes.remove(crime.getId());
    }

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }

        return sCrimeLab;
    }

    public List<Crime> getCrimes() {
        return new ArrayList<>(mCrimes.values());
    }

    public Crime getCrime(UUID uuid) {
        return mCrimes.get(uuid);
    }
}
