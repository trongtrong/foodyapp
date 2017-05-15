package androi30_b.foody2.View;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androi30_b.foody2.Controller.DangKiController;
import androi30_b.foody2.Model.ThanhVienModel;
import androi30_b.foody2.R;

/**
 * Created by Dell on 5/7/2017.
 */

public class DangKiActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnDangKi;
    FirebaseAuth firebaseAuth;
    EditText edEmailDK, edPassworDK, edNhapLaiPassworDK;
    ProgressDialog progressDialog;
    DangKiController dangKiController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dang_ki);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        btnDangKi = (Button) findViewById(R.id.btnDangKi);
        btnDangKi.setOnClickListener(this);
        edEmailDK = (EditText) findViewById(R.id.edEmailDK);
        edPassworDK = (EditText) findViewById(R.id.edPassworDK);
        edNhapLaiPassworDK = (EditText) findViewById(R.id.edNhapLaiPassworDK);

    }

    @Override
    public void onClick(View v) {
        progressDialog.setMessage(getString(R.string.xulyprogess));
        progressDialog.setIndeterminate(true);
        progressDialog.show();
        final String email = edEmailDK.getText().toString();
        String matkhau = edPassworDK.getText().toString();
        String nhaplaimatkhau = edNhapLaiPassworDK.getText().toString();
        String thongbaoloidangki = getString(R.string.thongbaoloidangki);
        if(email.trim().length() == 0){
            Toast.makeText(this,thongbaoloidangki += getString(R.string.email), Toast.LENGTH_SHORT).show();
        }else if (matkhau.trim().length() == 0){
            Toast.makeText(this,thongbaoloidangki += getString(R.string.matkhau), Toast.LENGTH_SHORT).show();
        }else if (!nhaplaimatkhau.equals(matkhau)){
            Toast.makeText(this,getString(R.string.thongbaonhaplaimatkhau), Toast.LENGTH_SHORT).show();
        }else {
            firebaseAuth.createUserWithEmailAndPassword(email,matkhau).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        progressDialog.dismiss();
                        ThanhVienModel thanhVienModel = new ThanhVienModel();
                        thanhVienModel.setHoten(email);
                        thanhVienModel.setHinhanh("user.png");
                        dangKiController = new DangKiController();
                        //lay uid vua tao
                        String uid = task.getResult().getUser().getUid();

                        dangKiController.themThanhVienMOdelController(thanhVienModel, uid);

                        Toast.makeText(DangKiActivity.this, getString(R.string.dangkithanhcong), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
