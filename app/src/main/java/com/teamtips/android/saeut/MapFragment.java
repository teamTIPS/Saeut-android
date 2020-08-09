package com.teamtips.android.saeut;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapReverseGeoCoder;
import net.daum.mf.map.api.MapView;

import static android.content.Context.LOCATION_SERVICE;

public class MapFragment extends Fragment implements MapView.CurrentLocationEventListener, MapReverseGeoCoder.ReverseGeoCodingResultListener {

  private static final String LOG_TAG = "MapFragment";

  private static final int GPS_ENABLE_REQUEST_CODE = 2001;
  private static final int PERMISSIONS_REQUEST_CODE = 100;
  String[] REQUIRED_PERMISSIONS  = {Manifest.permission.ACCESS_FINE_LOCATION};
  MapView mapView;
  MapPoint nowMapPoint;

  public static MapFragment newInstance() {
    return new MapFragment();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View v = inflater.inflate(R.layout.fragment_maps, container, false);

    //지도
    mapView = new MapView(getContext());
    ViewGroup mapViewContainer = (ViewGroup) v.findViewById(R.id.map_view);
    mapViewContainer.addView(mapView);

    mapView.setCurrentLocationEventListener(this);

    if (!checkLocationServicesStatus()) {
      //showDialogForLocationServiceSetting();
    } else {
      checkRunTimePermission();
    }

    // 중심점 변경 - 현위치
    mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(37.54892296550104, 126.99089033876304), true);

    // 줌 레벨 변경
    mapView.setZoomLevel(4, true);

    //마커 찍기
    MapPoint MARKER_POINT = MapPoint.mapPointWithGeoCoord(37.54892296550104, 126.99089033876304);
    MapPOIItem marker = new MapPOIItem();
    marker.setItemName("Default Marker");
    marker.setTag(0);
    marker.setMapPoint(MARKER_POINT);
    marker.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
    marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.

    mapView.addPOIItem(marker);

    return v;
  }


  @Override
  public void onDestroy() {
    super.onDestroy();
    mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOff);
    mapView.setShowCurrentLocationMarker(false);
  }

  @Override
  public void onCurrentLocationUpdate(MapView mapView, MapPoint currentLocation, float accuracyInMeters) {
    nowMapPoint = currentLocation;
  }


  @Override
  public void onCurrentLocationDeviceHeadingUpdate(MapView mapView, float v) {

  }

  @Override
  public void onCurrentLocationUpdateFailed(MapView mapView) {

  }

  @Override
  public void onCurrentLocationUpdateCancelled(MapView mapView) {

  }

  /*
   * ActivityCompat.requestPermissions를 사용한 퍼미션 요청의 결과를 리턴받는 메소드입니다.
   */
  @Override
  public void onRequestPermissionsResult(int permsRequestCode,
                                         @NonNull String[] permissions,
                                         @NonNull int[] grandResults) {

    if ( permsRequestCode == PERMISSIONS_REQUEST_CODE && grandResults.length == REQUIRED_PERMISSIONS.length) {

      // 요청 코드가 PERMISSIONS_REQUEST_CODE 이고, 요청한 퍼미션 개수만큼 수신되었다면

      boolean check_result = true;


      // 모든 퍼미션을 허용했는지 체크합니다.

      for (int result : grandResults) {
        if (result != PackageManager.PERMISSION_GRANTED) {
          check_result = false;
          break;
        }
      }


      if ( check_result ) {
        Log.d("@@@", "start");
        //위치 값을 가져올 수 있음
        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithHeading);
      }
      else {
        // 거부한 퍼미션이 있다면 앱을 사용할 수 없는 이유를 설명해주고 앱을 종료합니다.2 가지 경우가 있습니다.

        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), REQUIRED_PERMISSIONS[0])) {
          Toast.makeText(this.getContext(), "퍼미션이 거부되었습니다. 앱을 다시 실행하여 퍼미션을 허용해주세요.", Toast.LENGTH_LONG).show();
        }else {
          Toast.makeText(this.getContext(), "퍼미션이 거부되었습니다. 설정(앱 정보)에서 퍼미션을 허용해야 합니다. ", Toast.LENGTH_LONG).show();
        }
      }

    }
  }

  void checkRunTimePermission(){

    //런타임 퍼미션 처리
    // 1. 위치 퍼미션을 가지고 있는지 체크합니다.
    int hasFineLocationPermission = ContextCompat.checkSelfPermission(this.getContext(),
            Manifest.permission.ACCESS_FINE_LOCATION);

    if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED ) {

      // 2. 이미 퍼미션을 가지고 있다면
      // ( 안드로이드 6.0 이하 버전은 런타임 퍼미션이 필요없기 때문에 이미 허용된 걸로 인식합니다.)

      // 3.  위치 값을 가져올 수 있음
      mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithHeading);


    } else {  //2. 퍼미션 요청을 허용한 적이 없다면 퍼미션 요청이 필요합니다. 2가지 경우(3-1, 4-1)가 있습니다.

      // 3-1. 사용자가 퍼미션 거부를 한 적이 있는 경우에는
      if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), REQUIRED_PERMISSIONS[0])) {

        // 3-2. 요청을 진행하기 전에 사용자가에게 퍼미션이 필요한 이유를 설명해줄 필요가 있습니다.
        Toast.makeText(this.getContext(), "이 앱을 실행하려면 위치 접근 권한이 필요합니다.", Toast.LENGTH_LONG).show();
        // 3-3. 사용자게에 퍼미션 요청을 합니다. 요청 결과는 onRequestPermissionResult에서 수신됩니다.
        ActivityCompat.requestPermissions(getActivity(), REQUIRED_PERMISSIONS,
                PERMISSIONS_REQUEST_CODE);


      } else {
        // 4-1. 사용자가 퍼미션 거부를 한 적이 없는 경우에는 퍼미션 요청을 바로 합니다.
        // 요청 결과는 onRequestPermissionResult에서 수신됩니다.
        ActivityCompat.requestPermissions(getActivity(), REQUIRED_PERMISSIONS,
                PERMISSIONS_REQUEST_CODE);
      }

    }

  }

  //여기부터는 GPS 활성화를 위한 메소드들
  private void showDialogForLocationServiceSetting() {

    AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
    builder.setTitle("위치 서비스 비활성화");
    builder.setMessage("앱을 사용하기 위해서는 위치 서비스가 필요합니다.\n"
            + "위치 설정을 수정하실래요?");
    builder.setCancelable(true);
    builder.setPositiveButton("설정", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int id) {
        Intent callGPSSettingIntent
                = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivityForResult(callGPSSettingIntent, GPS_ENABLE_REQUEST_CODE);
      }
    });
    builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int id) {
        dialog.cancel();
      }
    });
    builder.create().show();
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    switch (requestCode) {

      case GPS_ENABLE_REQUEST_CODE:

        //사용자가 GPS 활성 시켰는지 검사
        if (checkLocationServicesStatus()) {
          if (checkLocationServicesStatus()) {

            Log.d("@@@", "onActivityResult : GPS 활성화 되있음");
            checkRunTimePermission();
            return;
          }
        }

        break;
    }
  }

  public boolean checkLocationServicesStatus() {
    LocationManager locationManager = (LocationManager) this.getContext().getSystemService(LOCATION_SERVICE);

    return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
            || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
  }

  @Override
  public void onReverseGeoCoderFoundAddress(MapReverseGeoCoder mapReverseGeoCoder, String s) {

  }

  @Override
  public void onReverseGeoCoderFailedToFindAddress(MapReverseGeoCoder mapReverseGeoCoder) {

  }
}