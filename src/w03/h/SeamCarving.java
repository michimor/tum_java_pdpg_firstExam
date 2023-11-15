package w03.h;

import java.util.Arrays;

public class SeamCarving {

	public int computeGradientMagnitude(int v1, int v2) {
		int deltaRed = getRedChannel(v1) - getRedChannel(v2);
		int deltaGreen = getGreenChannel(v1) - getGreenChannel(v2);
		int deltaBlue = getBlueChannel(v1) - getBlueChannel(v2);
		return (deltaRed*deltaRed) + (deltaGreen*deltaGreen) + (deltaBlue*deltaBlue);
	}

	public void toGradientMagnitude(int[] picture, int[] gradientMagnitude, int width, int height) {
		if (width > 0 && height > 0) {
//			iterate over picture in x direction
			for (int y = 0; y < height; y++) {
				for (int x = 1; x < (width-1); x++) {
					int left = picture[getIndex(x-1, y, width)];
					int right = picture[getIndex(x+1, y, width)];
					gradientMagnitude[getIndex(x, y, width)] = computeGradientMagnitude(left, right);
				}
			}
//			iterate over picture in y direction
			for (int x = 0; x < width; x++) {
				for (int y = 1; y < (height-1); y++) {
					int top = picture[getIndex(x, y-1, width)];
					int bottom = picture[getIndex(x, y+1, width)];
					gradientMagnitude[getIndex(x, y, width)] = computeGradientMagnitude(top, bottom);
				}
			}
			computeBorders(gradientMagnitude, width, height);
		}
	}

	private int getIndex(int x,  int y, int width) {
		return y * width + x;
	}

	private void computeBorders(int[] gradientMagnitude, int width, int height) {
		for (int y = 0; y < height; y++) {
			gradientMagnitude[getIndex(0, y, width)] = Integer.MAX_VALUE;
			gradientMagnitude[getIndex(width-1, y, width)] = Integer.MAX_VALUE;
		}
		for (int x = 0; x < width; x++) {
			gradientMagnitude[getIndex(x, 0, width)] = Integer.MAX_VALUE;
			gradientMagnitude[getIndex(x, height-1, width)] = Integer.MAX_VALUE;
		}
	}


	public void combineMagnitudeWithMask(int[] gradientMagnitude, int[] mask, int width, int height) {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int maskValue = mask[getIndex(x, y, width)];
				if (getRedChannel(maskValue)+getGreenChannel(maskValue)+getBlueChannel(maskValue) == 0) {
					gradientMagnitude[getIndex(x, y, width)] = Integer.MAX_VALUE;
				}
			}
		}
	}

	public void buildSeams(int[][] seams, long[] seamWeights, int[] gradientMagnitude, int width, int height) {
		if (width > 0 && height > 0) {
//			init first seams row
			for (int x = 0; x < width; x++) {
				seams[x][0] = x;
				seamWeights[x] = gradientMagnitude[x];
			}
			for (int x = 0; x < width; x++) {
				int[] seam = seams[x];
				buildSeam(seam, seamWeights, gradientMagnitude, width, height, x);
			}
		}
	}

	private void buildSeam(int[] seam, long[] seamWeights, int[] gradientMagnitude, int width, int height, int x) {
		for (int y = 1; y < height; y++) {
			int currentSeamX = seam[y - 1];

			int leftNext = currentSeamX > 0 ? gradientMagnitude[getIndex(currentSeamX - 1, y, width)] : Integer.MAX_VALUE;
			int middleNext = gradientMagnitude[getIndex(currentSeamX, y, width)];
			int rightNext  = currentSeamX + 1 < width ? gradientMagnitude[getIndex(currentSeamX + 1, y, width)] : Integer.MAX_VALUE;

			// Middle has the lowest weight
			if (middleNext <= leftNext && middleNext <= rightNext) {
				seam[y] = currentSeamX;
				seamWeights[x] += middleNext;
			}
			else {
				// Left has the lowest weight
				if (leftNext <= rightNext) {
					seam[y] = currentSeamX - 1;
					seamWeights[x] += leftNext;
				}
				// Right has the lowest weight
				else {
					seam[y] = currentSeamX + 1;
					seamWeights[x] += rightNext;
				}
			}

		}
	}

	public void removeSeam(int[] seam, int[] image, int height, int oldWidth) {
		if (height == 0 || oldWidth < 2) {
			return;
		}
		int y = 0;
		int numberOfRemovedPixels = 0;
		int nextPixelIndexToRemove = getIndex(seam[y], y, oldWidth);
		for (int i=0; i<image.length; i++) {
			if (i == nextPixelIndexToRemove) {
				// Do not move i as it is being removed (it will be overwritten)
				numberOfRemovedPixels++;
				y++;
				if (y < height) {
					nextPixelIndexToRemove = getIndex(seam[y], y, oldWidth);
				}
			}
			else {
				// Each pixel moves to left in the places freed by removed pixels
				image[i-numberOfRemovedPixels] = image[i];
			}
		}
	}

	public int[] shrink(int[] image,int[] mask, int width, int height, int newWidth) {
		int[] ret;
		if (width > 0 && height > 0 && newWidth > 0) {
			int[] gradientMagnitude = new int[width * height];
			int[][] seams = new int[width][height];
			long[] seamWeights = new long[width];
			for (int currentWidth = width; currentWidth > newWidth; currentWidth--) {
				toGradientMagnitude(image, gradientMagnitude, currentWidth, height);
				combineMagnitudeWithMask(gradientMagnitude, mask, currentWidth, height);
				buildSeams(seams, seamWeights, gradientMagnitude, currentWidth, height);
				int seamIndex = getSmallestSeam(seamWeights, currentWidth);
				removeSeam(seams[seamIndex], image, height, currentWidth);
				removeSeam(seams[seamIndex], mask, height, currentWidth);
			}
			ret = Arrays.copyOf(image, newWidth*height);
		}
		else {
			ret = new int[0];
		}
		return ret;
	}

	private int getSmallestSeam(long[] seamWeights, int width) {
		int res = 0;
		for (int i = 0; i < width; i++) {
			if (seamWeights[i] < seamWeights[res]) {
				res = i;
			}
		}
		return res;
	}

	private int getRedChannel(int value) {
		return (value % 256);
	}

	private int getGreenChannel(int value) {
		return (value/256)%256;
	}

	private int getBlueChannel(int value) {
		return (value/(256*256))%256;
	}

}
