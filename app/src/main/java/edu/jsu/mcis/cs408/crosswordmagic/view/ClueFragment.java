package edu.jsu.mcis.cs408.crosswordmagic.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.beans.PropertyChangeEvent;

import edu.jsu.mcis.cs408.crosswordmagic.controller.CrosswordMagicController;
import edu.jsu.mcis.cs408.crosswordmagic.databinding.FragmentClueBinding;

public class ClueFragment extends Fragment implements AbstractView {
    public static final String ARG_ID = "id";
    private FragmentClueBinding binding;
    private CrosswordMagicController controller;
    private String cluesAcross;
    private String cluesDown;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentClueBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /* get controller, register Fragment as a View */
        this.controller = ((MainActivity)getContext()).getController();
        controller.addView(this);

        controller.getCluesAcross();
        controller.getCluesDown();
    }

    @Override
    public void modelPropertyChange(final PropertyChangeEvent evt) {

        String name = evt.getPropertyName();
        Object value = evt.getNewValue();

        if (name.equals(CrosswordMagicController.CLUES_ACROSS_PROPERTY)) {

            if (value instanceof String) {

                this.cluesAcross = (String) value;
                TextView view = binding.aContainer;
                view.setText(cluesAcross);



            }

        }

        if (name.equals(CrosswordMagicController.CLUES_DOWN_PROPERTY)) {

            if (value instanceof String) {

                this.cluesDown = (String) value;

                TextView view = binding.dContainer;
                view.setText(cluesDown);

            }

        }

    }
}
