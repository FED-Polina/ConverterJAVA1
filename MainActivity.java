package fedulova.polina303.converter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fedulova.polina303.converter.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding = null; //инициализируем объект привязки C Sharp в файле build.gradleModule

    private ArrayAdapter<MeasureUnit> adapter;//инициализация адаптера
    private ArrayList<MeasureUnit> lengths = new ArrayList<>();//инициализация и создание массива
    private ArrayList<MeasureUnit> squares = new ArrayList<>();
    private ArrayList<MeasureUnit> weights = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());//создание объекта привязки
        //setContentView(R.layout.activity_main); было
        setContentView(binding.getRoot()); //стало

        adapter = new ArrayAdapter<MeasureUnit>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);//создание адаптера для спиннера параметризированного созданным классом MeasureUnit

        lengths.add(new MeasureUnit(0.001, "мм"));//добавление элемента в массив с названием и коэффициентом
        lengths.add(new MeasureUnit(0.01, "см"));
        lengths.add(new MeasureUnit(0.1, "дм"));
        lengths.add(new MeasureUnit(1.0, "м"));
        lengths.add(new MeasureUnit(1000.0, "км"));

        squares.add(new MeasureUnit(0.000001, "мм2"));
        squares.add(new MeasureUnit(0.0001, "см2"));
        squares.add(new MeasureUnit(0.01, "дм2"));
        squares.add(new MeasureUnit(1.0, "м2"));
        squares.add(new MeasureUnit(1000000.0, "км2"));

        weights.add(new MeasureUnit(0.001, "мг"));
        weights.add(new MeasureUnit(1.0, "г"));
        weights.add(new MeasureUnit(1000.0, "кг"));
        weights.add(new MeasureUnit(100000.0, "ц"));
        weights.add(new MeasureUnit(1000000.0, "т"));


        adapter.addAll(lengths);//добавление в адаптер всех элементов массива lengths т к изначально выбрана radioButtonLength

        binding.spinnerFrom.setAdapter(adapter);//подключение адаптера к спиннеру From
        binding.spinnerTo.setAdapter(adapter);//подключение адаптера к спиннеру To

        metodOtslejivaniaNajatia();//метод в код добавляем слушатели на кнопки
    }

    private void metodOtslejivaniaNajatia() {
        binding.buttonConvert.setOnClickListener(new View.OnClickListener() /*установить слушатель нажатий на buttonConvert*/ {
            @Override
            public void onClick(View view) /*что происходит по нажатию кнопки*/ {
                if (!binding.editTextFrom.getText().toString().isEmpty()) {
                    Double result = Double.parseDouble(binding.editTextFrom.getText().toString()) * (adapter.getItem(binding.spinnerFrom.getSelectedItemPosition()).coefficient / adapter.getItem(binding.spinnerTo.getSelectedItemPosition()).coefficient);
                    binding.textViewTo.setText(result.toString());
                } else {
                    Toast.makeText(getApplicationContext(), "Поле не должно быть пустым", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.radioButtonLenght.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() /*установить слушатель на radioButtonLenght*/ {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true) /*если cheked == true*/{
                    adapter.clear();
                    adapter.addAll(lengths);
                }
            }
        });

        binding.radioButtonSquare.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() /*установить слушатель на radioButtonSquare*/ {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true) {
                    adapter.clear();
                    adapter.addAll(squares);
                }
            }
        });

        binding.radioButtonWeight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() /*установить слушатель на radioButtonWeight*/ {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true) {
                    adapter.clear();
                    adapter.addAll(weights);
                }
            }
        });
    }
}