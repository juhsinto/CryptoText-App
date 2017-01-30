package com.jacintomendes.cryptotext.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jacintomendes.cryptotext.MainActivity;
import com.jacintomendes.cryptotext.fragments.DecryptFragment;
import com.jacintomendes.cryptotext.fragments.EncryptFragment;

/**
 * Created by Jm on 19-Sep-14.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {
    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {
//        Fragment frag = new DummySectionFragment();
//        Bundle args = new Bundle();
//        args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, index + 1);
//        frag.setArguments(args);
//        return frag;

        switch (index) {
            case 0:
                // Encrypt fragment activity
                return new EncryptFragment();
            case 1:
                // Decrypt fragment activity
                return new DecryptFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        return MainActivity.MAX_TABS;
    }

//    public static class DummySectionFragment extends Fragment {
//        /**
//         * The fragment argument representing the section number for this
//         * fragment.
//         */
//        public static final String ARG_SECTION_NUMBER = "section_number";
//
//        public DummySectionFragment() {
//        }
//
//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
//            TextView dummyTextView = (TextView) rootView.findViewById(R.id.section_label);
//            dummyTextView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
//            return rootView;
//        }
//    }
}

