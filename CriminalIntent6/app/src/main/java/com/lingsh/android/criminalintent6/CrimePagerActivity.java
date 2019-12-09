package com.lingsh.android.criminalintent6;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;
import java.util.UUID;

public class CrimePagerActivity extends AppCompatActivity {

    // 自产自销
    private static final String EXTRA_CRIME_ID = "com.lingsh.android.criminalintent.crime_id";

    private ViewPager mViewPager;
    private Button mToFirstButton;
    private Button mToLastButton;
    private List<Crime> mCrimes;

    public static Intent newIntent(Context packageContext, UUID crimeId) {
        Intent intent = new Intent(packageContext, CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);

        mViewPager = findViewById(R.id.activity_crime_pager_view_pager);
        mToFirstButton = findViewById(R.id.pager_view_to_first_button);
        mToLastButton = findViewById(R.id.pager_view_to_last_button);
        mCrimes = CrimeLab.get(this).getCrimes();

        mToFirstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(0);
            }
        });

        mToLastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(mCrimes.size() - 1);
            }
        });

        FragmentManager fragmentManager = getSupportFragmentManager();
        // Note: FragmentStatePagerAdapter 与 FragmentPagerAdapter
        // FragmentStatePagerAdapter会消耗不需要的Fragment remove 更节省内存(如果需要显示大量记录, 每份记录存储大量信息)
        // FragmentPagerAdapter不会消耗Fragment detach 适合少量固定的Fragment 更安全
        // FragmentStatePagerAdapter(FragmentManager) 这个构造函数已经被遗弃(在androidx)
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                Crime crime = mCrimes.get(position);
                return CrimeFragment.newInstance(crime.getId());
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == 0) {
                    // 在第一页
                    mToFirstButton.setVisibility(View.INVISIBLE);
                    mToLastButton.setVisibility(View.VISIBLE);
                } else if (position == mCrimes.size() - 1) {
                    // 在末一页
                    mToFirstButton.setVisibility(View.VISIBLE);
                    mToLastButton.setVisibility(View.INVISIBLE);
                } else {
                    // 在中间页
                    mToFirstButton.setVisibility(View.VISIBLE);
                    mToLastButton.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
        for (int i = 0; i < mCrimes.size(); i++) {
            if (mCrimes.get(i).getId().equals(crimeId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }


}
