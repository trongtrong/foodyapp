package androi30_b.foody2.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

import androi30_b.foody2.R;

/**
 * Created by Dell on 5/9/2017.
 */

public class KhoiPhucMatKhauActivity extends AppCompatActivity implements View.OnClickListener {
    TextView txtDangKiKP;
    Button btnGuiMail;
    EditText edEmailKP;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_quenmatkhau);

        firebaseAuth = FirebaseAuth.getInstance();

        txtDangKiKP = (TextView) findViewById(R.id.txtDangKiKP);
        btnGuiMail = (Button) findViewById(R.id.btnGuiMail);
        edEmailKP = (EditText) findViewById(R.id.edEmailKP);

        btnGuiMail.setOnClickListener(this);
        txtDangKiKP.setOnClickListener(this);
    }
    private boolean kiemTraEmail(String email){
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnGuiMail:
                String email = edEmailKP.getText().toString();
                boolean kiemtraemail = kiemTraEmail(email);
                if (kiemtraemail){
                    firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(KhoiPhucMatKhauActivity.this, getString(R.string.thongbaoemail), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }else {
                    Toast.makeText(this, getString(R.string.thongbaoloiemail), Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.txtDangKiKP:
                Intent iDangKi = new Intent(this, DangKiActivity.class);
                startActivity(iDangKi);
        }
    }
}
