package gr.uoa.ec.ismini.productDetail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;
import gr.uoa.ec.ismini.R;
import gr.uoa.ec.ismini.shoppingList.DummyShoppingList;
import gr.uoa.ec.ismini.models.Product;

public class ProductDetailActivity extends AppCompatActivity {

    TextView productPriceTextView;
    TextView productDescriptionTextView;
    TextView productPreparationTextView;
    Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        productPriceTextView = (TextView) findViewById(R.id.product_detail_price);
        productDescriptionTextView = (TextView) findViewById(R.id.product_detail_description);
        productPreparationTextView = (TextView) findViewById(R.id.product_detail_preparation);

        Bundle extras = getIntent().getExtras();
        try {
            if (extras != null) {

                String jsonProduct = extras.getString("product");
                product = new Gson().fromJson(jsonProduct, Product.class);

                this.setTitle(product.getName());
                productPriceTextView.setText(String.valueOf(product.getPrice()));
                productDescriptionTextView.setText(product.getDescription());
                productPreparationTextView.setText(String.valueOf(product.getPreparation()));
            }

            final Button addToBasketBtn = (Button) findViewById(R.id.add_to_basket_button);
            addToBasketBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (product != null) {
                        DummyShoppingList.add(product);
                    }
                }
            });
        } catch (Exception e) {
            Toast.makeText(ProductDetailActivity.this, "Oops... Something went wrong. Please try again", Toast.LENGTH_LONG).show();
            Log.e(ProductDetailActivity.class.toString(), e.toString());
        }
    }
}
