package baali.org.savingimage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment
{
    private static final String TAG = MainActivity.class.getSimpleName();

    @Bind(R.id.myImage)
    ImageView imageView;

    String imageUrl = "http://www.sportstarlive.com/multimedia/dynamic/02718/31_SSLIVE_YUVI-RAI_2718547c.jpg";

    public MainActivityFragment()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        usingPlainPicasso();
        return view;
    }

    void usingPlainPicasso()
    {
        OkHttpClient client = new OkHttpClient();
        Request request =  new Request.Builder()
                .url(imageUrl).build();
        client.newCall(request).enqueue(new Callback()
        {
            @Override
            public void onFailure(Call call, IOException e)
            {
                Log.e(TAG, "onFailured: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException
            {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                Headers responseHeaders = response.headers();
                for (int i = 0; i < responseHeaders.size(); i++) {
                    Log.d(TAG, "onResponse: "+ responseHeaders.name(i) + ": " + responseHeaders.value(i));
                }
            }
        });

    }
}
