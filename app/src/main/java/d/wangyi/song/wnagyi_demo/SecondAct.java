package d.wangyi.song.wnagyi_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

public class SecondAct extends AppCompatActivity implements View.OnClickListener{

    private RadioGroup rg1,rg2,rg3,rg4,rg5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        rg1 = (RadioGroup) findViewById(R.id.gradio_group);
        rg2 = (RadioGroup) findViewById(R.id.gradio_group2);
        rg3 = (RadioGroup) findViewById(R.id.gradio_group3);
        rg4 = (RadioGroup) findViewById(R.id.gradio_group4);
        rg5 = (RadioGroup) findViewById(R.id.gradio_group5);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.gradio_group:

                break;
            case R.id.gradio_group2:

                break;
            case R.id.gradio_group3:

                break;
            case R.id.gradio_group4:

                break;
            case R.id.gradio_group5:

                break;

        }
    }
}
