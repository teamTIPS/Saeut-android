package com.teamtips.android.saeut.ui.profile;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.teamtips.android.saeut.R;
import com.teamtips.android.saeut.RequestHttpURLConnection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel = new ProfileViewModel();
    TextView tv1;
    TextView tv2;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        //final TextView textView = root.findViewById(R.id.text_profile);
        profileViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
             //   textView.setText(s);
            }
        });
        tv1 = root.findViewById(R.id.textView1);
        tv2 = root.findViewById(R.id.textView2);
        NetworkTask networkTask = new NetworkTask("http://49.50.173.180:8080/saeut/account/test",null);
        networkTask.execute();

        return root;
    }

    public class NetworkTask extends AsyncTask<Void, Void, String> {

        private String url;
        private ContentValues values;


        public NetworkTask(String url, ContentValues values) {

            this.url = url;
            this.values = values;
        }

        @Override
        protected String doInBackground(Void... params) {

            String result; // 요청 결과를 저장할 변수.
            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            result = requestHttpURLConnection.request(url, values); // 해당 URL로 부터 결과물을 얻어온다.

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                    JSONObject object = new JSONObject(s);
                    String id = object.getString("id");
                    String pw = object.getString("password");
                    tv1.setText(id);
                    tv2.setText(pw);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    } //NetworkTask

}
