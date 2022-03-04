package com.example.proyectomontpedidosinicial.utils;


import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class ActivityUtils {


    public static void addFragmentToActivity (@NonNull FragmentManager fragmentManager,
                                              @NonNull Fragment fragment, int frameId) {
        //checkNotNull(fragmentManager);
        //checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment, "Home");
        transaction.addToBackStack("Home");
        transaction.commit();
    }


    public static void addFragment (@NonNull FragmentManager fragmentManager,
                                    @NonNull Fragment fragment, int frameId, String tag) {
        //checkNotNull(fragmentManager);
        //checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment, tag);
        transaction.commit();
    }


    public static void addFragmentToFragment (@NonNull FragmentManager fragmentManager,
                                              @NonNull Fragment fragment, int frameId, String tag) {
        //checkNotNull(fragmentManager);
        //checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment, tag);
        transaction.addToBackStack(tag);
        transaction.commit();
    }


    public static void replaceFragment(@NonNull final FragmentManager fragmentManager,
                                       @NonNull Fragment fragment, int frameId) {
        //checkNotNull(fragmentManager);
        //checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(frameId, fragment);
        transaction.commit();
    }

    public static void removeFragment(@NonNull FragmentManager fragmentManager,
                                      @NonNull Fragment fragment) {
        //checkNotNull(fragmentManager);
        //checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.remove(fragment);
        transaction.commitAllowingStateLoss();
    }

}