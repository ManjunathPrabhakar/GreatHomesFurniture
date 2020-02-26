package com.example.dailyfoods;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.Renderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ArFragment fragment;
    private Uri selectedObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initilize Fragment Element as ARFragment
        fragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.sceneform_fragment);

        //Intilize Linear Layout and Create ImageView(s)
        //InitializeGallery();

        Intent intent = getIntent();
        selectedObject = getIntent().getData();

        fragment.setOnTapArPlaneListener(
                (HitResult hitResult, Plane plane, MotionEvent motionEvent) -> {

                    if (plane.getType() != Plane.Type.HORIZONTAL_UPWARD_FACING) {
                        return;
                    }

                    Anchor anchor = hitResult.createAnchor();

                    placeObject(fragment, anchor, selectedObject);
                }
        );
    }

//    private void InitializeGallery() {
//        LinearLayout gallery = findViewById(R.id.gallery_layout);
//        Map<String, Integer> list = new HashMap<>();
//        list.put("chair@chair.sfb", R.drawable.chair_thumb);
//        list.put("couch@couch.sfb", R.drawable.couch_thumb);
//        list.put("lamp@LampPost.sfb", R.drawable.lamp_thumb);
//        list.put("desk@Desk.sfb", R.drawable.officetable);
//
//        for (Map.Entry<String, Integer> entry : list.entrySet()) {
//            String[] val = entry.getKey().split("@");
//            String conDesc = val[0];
//            String sfbFile = val[1];
//            int drawableImage = entry.getValue();
//
//            ImageView imageView = new ImageView(this);
//
//            LinearLayout.LayoutParams deletelayoutParams = new LinearLayout.LayoutParams(128, 128);
//            imageView.setLayoutParams(deletelayoutParams);
//
//            imageView.setImageResource(drawableImage);
//            imageView.setContentDescription(conDesc);
//            imageView.setOnClickListener(view -> {
//                selectedObject = Uri.parse(sfbFile);
//            });
//            gallery.addView(imageView);
//
//
//        }
//
//
////
////        ImageView couch = new ImageView(this);
////        couch.setImageResource(R.drawable.couch_thumb);
////        couch.setContentDescription("couch");
////        couch.setOnClickListener(view -> {
////            selectedObject = Uri.parse("couch.sfb");
////        });
////        gallery.addView(couch);
////
////        ImageView lampPost = new ImageView(this);
////        lampPost.setImageResource(R.drawable.lamp_thumb);
////        lampPost.setContentDescription("lampPost");
////        lampPost.setOnClickListener(view -> {
////            selectedObject = Uri.parse("LampPost.sfb");
////        });
////        gallery.addView(lampPost);
//    }


    private void placeObject(ArFragment fragment, Anchor anchor, Uri model) {
        ModelRenderable.builder()
                .setSource(fragment.getContext(), model)
                .build()
                .thenAccept(renderable -> addNodeToScene(fragment, anchor, renderable))
                .exceptionally((throwable -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage(throwable.getMessage())
                            .setTitle("Error!");
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    return null;
                }));
    }

    private void addNodeToScene(ArFragment fragment, Anchor anchor, Renderable renderable) {
        AnchorNode anchorNode = new AnchorNode(anchor);
        TransformableNode transformableNode = new TransformableNode(fragment.getTransformationSystem());
        transformableNode.setRenderable(renderable);
        transformableNode.setParent(anchorNode);
        fragment.getArSceneView().getScene().addChild(anchorNode);
        transformableNode.select();
    }
}
