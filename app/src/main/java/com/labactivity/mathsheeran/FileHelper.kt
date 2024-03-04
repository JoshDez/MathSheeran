package com.labactivity.mathsheeran

import android.content.Context
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class FileHelper {
    private val fileName = "listinfo.dat"

    fun writeData(itemList:ArrayList<Long>, context:Context){
        try{
            val fos: FileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE)
            val oas = ObjectOutputStream(fos)
            oas.writeObject(itemList)
            oas.close()
        } catch (e:FileNotFoundException){
            e.printStackTrace()
        } catch (e:IOException){
            e.printStackTrace()
        }
    }

    fun readData(context: Context):ArrayList<Long>{
        lateinit var list:ArrayList<Long>
        try{
            val fis: FileInputStream = context.openFileInput(fileName)
            val ois = ObjectInputStream(fis)
            list = ois.readObject() as ArrayList<Long>
        } catch (e:FileNotFoundException){
            list = ArrayList()
            //Addition default score
            list.add(0)
            //Subtraction default score
            list.add(0)
            //Multiplication default score
            list.add(0)
        } catch (e:IOException){
            e.printStackTrace()
        } catch (e:ClassNotFoundException){
            e.printStackTrace()
        }
        return list
    }
}