/**
 * @author Rafael Oliveira
 *
 * Está Activity tem por finalidade apresentar todos os integrantes do trabalho.
 *
 * @see android.support.v4.app.Fragment
 * @link https://developer.android.com/reference/android/support/v4/app/Fragment
 *
 * @see android.view.LayoutInflater
 * @link https://developer.android.com/reference/android/view/LayoutInflater
 *
 * @see android.view.View
 * @link https://developer.android.com/reference/android/view/View
 *
 * @see android.view.ViewGroup
 * @link https://developer.android.com/reference/android/view/ViewGroup
 *
 */

package rafael.controlhome.controller.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import rafael.controlhome.R;

public class FragmentAbout extends Fragment {

    public static FragmentAbout newInstance() {
        FragmentAbout fragment = new FragmentAbout();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_about, container, false);
    }
}
