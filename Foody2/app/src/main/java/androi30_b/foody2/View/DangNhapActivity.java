package androi30_b.foody2.View;

import android.content.Intent;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Arrays;
import java.util.List;

import androi30_b.foody2.R;

public class DangNhapActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener, FirebaseAuth.AuthStateListener{
    Button btnDangNhapGoogle;
    GoogleApiClient apiClient;
    Button btnDangNhapFacebook;
    public static int REQUEST_CODE_DANG_NHAP_GOOGLE = 99;
    public static int KIEM_TRA_PROVIDER_DANG_NHAP = 0;
    FirebaseAuth firebaseAuth;
    CallbackManager mCallbackManager;
    LoginManager loginManager;
    List<String> permissionsFacebook = Arrays.asList("email", "public_profile");
    TextView txtQuenMatKhau, txtDangKi;
    Button btnDangNhap;
    EditText edEmailDN, edPassworDN;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_dang_nhap);
        firebaseAuth = FirebaseAuth.getInstance();

        edEmailDN = (EditText) findViewById(R.id.edEmailDN);
        edPassworDN = (EditText) findViewById(R.id.edPassworDN);
        btnDangNhap  = (Button) findViewById(R.id.btnDangNhap);
        btnDangNhapGoogle = (Button) findViewById(R.id.btnDangNhapGoogle);
        btnDangNhapFacebook = (Button) findViewById(R.id.btnDangNhapFacebook);
        txtDangKi = (TextView) findViewById(R.id.txtDangKi);
        txtQuenMatKhau = (TextView) findViewById(R.id.txtQuenMatKhau);

        mCallbackManager = new CallbackManager.Factory().create();
        loginManager = LoginManager.getInstance();

        btnDangNhap.setOnClickListener(this);
        btnDangNhapGoogle.setOnClickListener(this);
        btnDangNhapFacebook.setOnClickListener(this);
        txtDangKi.setOnClickListener(this);
        txtQuenMatKhau.setOnClickListener(this);

        taoClientDangNhapGoogle();


    }
    private void dangNhapFacebook(){
        loginManager.logInWithReadPermissions(this, permissionsFacebook);
        loginManager.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                KIEM_TRA_PROVIDER_DANG_NHAP = 2;
                String tokenID = loginResult.getAccessToken().getToken();
                chungThucDangNHapFirebase(tokenID);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }
    private void taoClientDangNhapGoogle(){
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder()
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
         apiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build();
    }
    private void dangNhapGoogle(GoogleApiClient apiClient){
        KIEM_TRA_PROVIDER_DANG_NHAP = 1;
        Intent iDangNhapGoogle = Auth.GoogleSignInApi.getSignInIntent(apiClient);
        startActivityForResult(iDangNhapGoogle, REQUEST_CODE_DANG_NHAP_GOOGLE );
    }
    private void chungThucDangNHapFirebase(String tokenID){
        if (KIEM_TRA_PROVIDER_DANG_NHAP == 1){
            AuthCredential credential = GoogleAuthProvider.getCredential(tokenID, null);
            firebaseAuth.signInWithCredential(credential);

        }else if(KIEM_TRA_PROVIDER_DANG_NHAP == 2){
            AuthCredential credential = FacebookAuthProvider.getCredential(tokenID);
            firebaseAuth.signInWithCredential(credential);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_DANG_NHAP_GOOGLE){
            if (resultCode == RESULT_OK){
                GoogleSignInResult signInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                GoogleSignInAccount signInAccount = signInResult.getSignInAccount();
                String tokenID = signInAccount.getIdToken();
                chungThucDangNHapFirebase(tokenID);
            }
        }else{
            mCallbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnDangNhapGoogle :
                dangNhapGoogle(apiClient);
                break;
            case R.id.btnDangNhapFacebook:
                dangNhapFacebook();
                break;
            case R.id.txtDangKi:
                Intent iDangKi = new Intent(DangNhapActivity.this, DangKiActivity.class);
                startActivity(iDangKi);
                break;
            case R.id.btnDangNhap :
                dangNhap();
                break;
            case R.id.txtQuenMatKhau:
                Intent iKhoiPhucMatKhau = new Intent(DangNhapActivity.this, KhoiPhucMatKhauActivity.class);
                startActivity(iKhoiPhucMatKhau);
                break;
        }
    }
    private void dangNhap(){
        String email = edEmailDN.getText().toString();
        String matkhau = edPassworDN.getText().toString();
        firebaseAuth.signInWithEmailAndPassword(email, matkhau).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()){
                    Toast.makeText(DangNhapActivity.this, getString(R.string.loidangnhap), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null){
            Intent iTrangChu = new Intent(this, TrangChuActivity.class);
            startActivity(iTrangChu);
        }else {

        }
    }
}
