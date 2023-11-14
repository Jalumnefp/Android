package es.initialslayouts

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import es.initialslayouts.databinding.FragmentCalculatorBinding
import java.lang.StringBuilder


class CalculatorFragment : Fragment(), OnClickListener {

    private var _binding: FragmentCalculatorBinding? = null
    private val binding get() = _binding!!

    private var operation_line: StringBuilder = StringBuilder()
    private var operation: Char? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCalculatorBinding.inflate(inflater, container, false)
        setOnClickListeners()

        return binding.root
    }

    private fun setOnClickListeners() {
        val botonsCalculadora: List<Button> = listOf(
            binding.numberOneBtn, binding.numberTwoBtn, binding.numberThreeBtn,
            binding.numberFourBtn, binding.numberFiveBtn, binding.numberSixBtn,
            binding.numberSevenBtn, binding.numberEightBtn, binding.numberNineBtn,
            binding.numberZeroBtn, binding.operationAddBtn, binding.operationSubstractBtn, 
            binding.operationMultiplyBtn, binding.operationDivisionBtn, binding.signDecimalBtn, 
            binding.operationDivisionBtn, binding.operationResultBtn
        )

        botonsCalculadora.forEach { it.setOnClickListener(this) }

    }

    override fun onClick(v: View) {
        when(v) {
            binding.numberOneBtn -> onClickNumberButton(1)
            binding.numberTwoBtn -> onClickNumberButton(2)
            binding.numberThreeBtn -> onClickNumberButton(3)
            binding.numberFourBtn -> onClickNumberButton(4)
            binding.numberFiveBtn -> onClickNumberButton(5)
            binding.numberSixBtn -> onClickNumberButton(6)
            binding.numberSevenBtn -> onClickNumberButton(7)
            binding.numberEightBtn -> onClickNumberButton(8)
            binding.numberNineBtn -> onClickNumberButton(9)
            binding.numberZeroBtn -> onClickNumberButton(0)
            binding.operationAddBtn -> onClickOperationButton('+')
            binding.operationSubstractBtn -> onClickOperationButton('-')
            binding.operationMultiplyBtn -> onClickOperationButton('*')
            binding.operationDivisionBtn -> onClickOperationButton('/')
            binding.signDecimalBtn -> clearOperationLine()
            binding.operationResultBtn -> calculate()
        }
    }

    private fun setOperationInResultText(value: String) {
        binding.textViewResult.text = value
    }

    private fun onClickNumberButton(num: Int) {
        operation_line.append(num)
        setOperationInResultText(operation_line.toString())
    }

    private fun onClickOperationButton(sig: Char) {
        operation_line.append(sig)
        operation = sig
        setOperationInResultText(operation_line.toString())
    }

    private fun clearOperationLine(){
        setOperationInResultText("0")
        operation_line.setLength(0)
    }

    private fun calculate() {
        if (operation_line.toString() != "0/0") {
            val number: List<Int> = operation_line.toString().split(operation.toString()).map { it.toInt() }
            val result: Int = when(operation) {
                '+' -> Math.addExact(number[0], number[1])
                '-' -> Math.subtractExact(number[0], number[1])
                '*' -> Math.multiplyExact(number[0], number[1])
                '/' -> Math.floorDiv(number[0], number[1])
                else -> return
            }
            Log.d("TEST", "${number[0]} $operation ${number[1]} = $result")
            setOperationInResultText(result.toString())
            operation_line.setLength(0)
            operation_line.append(result)
        } else {
            clearOperationLine()
        }
    }

}