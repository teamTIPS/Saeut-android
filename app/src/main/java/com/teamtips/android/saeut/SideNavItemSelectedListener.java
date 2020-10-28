package com.teamtips.android.saeut;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.teamtips.android.saeut.func.location.setLocation;
import com.teamtips.android.saeut.func.login.join.ui.generalLogin.LoginActivity;
import com.teamtips.android.saeut.func.profile.editProfile;

public class SideNavItemSelectedListener
    implements NavigationView.OnNavigationItemSelectedListener {

  private final DrawerLayout drawer;
  private final BottomNavigationView navigation;
  private final Context context;

  public SideNavItemSelectedListener(DrawerLayout drawer, BottomNavigationView navigation, Context context) {
    this.drawer = drawer;
    this.navigation = navigation;
    this.context = context;
  }

  @Override
  public boolean onNavigationItemSelected(@NonNull MenuItem item) {
    // Handle navigation view item clicks here.
    int id = item.getItemId();
    if (id == R.id.nav_setLocation) {
      showSetLocationPage();
    } else if (id == R.id.nav_share) {
      showShareSnackBar();
    } else if (id == R.id.nav_gallery) {
      showGallerySnackBar();
    } else if (id == R.id.nav_send) {
      showSendSnackBar();
    } else if (id == R.id.nav_editProfile) {
      showEditProfilePage();
    } else if (id == R.id.nav_logout) {
      showLoginPage();
    }
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }

  private void showSendSnackBar() {
    Snackbar.make(navigation, "Send", Snackbar.LENGTH_SHORT).show();
  }

  private void showGallerySnackBar() {
    Snackbar.make(navigation, "Gallery", Snackbar.LENGTH_SHORT).show();
  }

  private void showToolSnackBar() {
    Snackbar.make(navigation, "Tool", Snackbar.LENGTH_SHORT).show();
  }

  private void showShareSnackBar() {
    Snackbar.make(navigation, "Share", Snackbar.LENGTH_SHORT).show();
  }

  private void showEditProfilePage() {
    Intent intent = new Intent(context, editProfile.class);
    context.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
  }

  private void showSetLocationPage() {
    Intent intent = new Intent(context, setLocation.class);
    context.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
  }

  private void showLoginPage() {
    Intent intent = new Intent(context, LoginActivity.class);
    context.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
  }
}
